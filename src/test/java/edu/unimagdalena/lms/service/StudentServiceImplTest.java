package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.StudentDtos.*;
import edu.unimagdalena.lms.entites.Student;
import edu.unimagdalena.lms.Repositories.StudentRepository;
import edu.unimagdalena.lms.service.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    StudentRepository repo;

    @InjectMocks
    StudentServiceImpl service;

    @Spy
    private StudentMapper mapper = Mappers.getMapper(StudentMapper.class);

    @Test
    void shouldCreateAndReturnResponseDto() {
        var req = new StudentCreateRequest("Ana Torres", "ana@uni.edu");
        when(repo.save(any())).thenAnswer(inv -> {
            Student s = inv.getArgument(0);
            s.setId(11L);
            return s;
        });

        var res = service.create(req);

        assertThat(res.id()).isEqualTo(11L);
        assertThat(res.email()).isEqualTo("ana@uni.edu");
        verify(repo).save(any(Student.class));
    }

    @Test
    void shouldThrowWhenStudentNotFound() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.get(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }
}
