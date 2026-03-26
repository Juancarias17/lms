package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.InstructorDtos.*;
import edu.unimagdalena.lms.entites.Instructor;
import edu.unimagdalena.lms.Repositories.InstructorRepository;
import edu.unimagdalena.lms.service.mapper.InstructorMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorServiceImplTest {

    @Mock
    InstructorRepository repo;

    @InjectMocks
    InstructorServiceImpl service;

    @Spy
    private InstructorMapper mapper = Mappers.getMapper(InstructorMapper.class);

    @Test
    void shouldCreateAndReturnResponseDto() {
        var req = new InstructorCreateRequest("Carlos Ruiz", "carlos@uni.edu");
        when(repo.save(any())).thenAnswer(inv -> {
            Instructor i = inv.getArgument(0);
            i.setId(11L);
            return i;
        });

        var res = service.create(req);

        assertThat(res.id()).isEqualTo(11L);
        assertThat(res.email()).isEqualTo("carlos@uni.edu");
        verify(repo).save(any(Instructor.class));
    }

    @Test
    void shouldThrowWhenInstructorNotFound() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.get(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }
}
