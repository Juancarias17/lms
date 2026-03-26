package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.InstructorProfileDtos.*;
import edu.unimagdalena.lms.entites.Instructor;
import edu.unimagdalena.lms.entites.InstructorProfile;
import edu.unimagdalena.lms.Repositories.InstructorProfileRepository;
import edu.unimagdalena.lms.Repositories.InstructorRepository;
import edu.unimagdalena.lms.service.mapper.InstructorProfileMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstructorProfileServiceImplTest {

    @Mock InstructorProfileRepository instructorProfileRepo;
    @Mock InstructorRepository instructorRepo;

    @InjectMocks
    InstructorProfileServiceImpl service;

    @Spy
    private InstructorProfileMapper mapper = Mappers.getMapper(InstructorProfileMapper.class);

    @Test
    void shouldCreateAndReturnResponseDto() {
        var req = new InstructorProfileCreateRequest(1L, "3001234567", "Experto en Java");
        Instructor instructor = Instructor.builder().id(1L).fullName("Carlos Ruiz").build();

        when(instructorRepo.findById(1L)).thenReturn(Optional.of(instructor));
        when(instructorProfileRepo.save(any())).thenAnswer(inv -> {
            InstructorProfile p = inv.getArgument(0);
            p.setId(6L);
            return p;
        });

        var res = service.create(req);

        assertThat(res.id()).isEqualTo(6L);
        assertThat(res.phone()).isEqualTo("3001234567");
        assertThat(res.instructorId()).isEqualTo(1L);
        verify(instructorProfileRepo).save(any(InstructorProfile.class));
    }

    @Test
    void shouldThrowWhenInstructorNotFound() {
        when(instructorRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.create(new InstructorProfileCreateRequest(99L, "000", "bio")))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }
}
