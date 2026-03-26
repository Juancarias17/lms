package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.AssessmentDtos.*;
import edu.unimagdalena.lms.entites.Assessment;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Student;
import edu.unimagdalena.lms.Repositories.AssessmentRepository;
import edu.unimagdalena.lms.Repositories.CourseRepository;
import edu.unimagdalena.lms.Repositories.StudentRepository;
import edu.unimagdalena.lms.service.mapper.AssessmentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssessmentServiceImplTest {

    @Mock AssessmentRepository assessmentRepo;
    @Mock StudentRepository studentRepo;
    @Mock CourseRepository courseRepo;

    @InjectMocks
    AssessmentServiceImpl service;

    @Spy
    private AssessmentMapper mapper = Mappers.getMapper(AssessmentMapper.class);

    @Test
    void shouldCreateAndReturnResponseDto() {
        var req = new AssessmentCreateRequest(1L, 2L, "EXAM", "95");
        Student student = Student.builder().id(1L).fullName("Pedro Ramirez").build();
        Course course = Course.builder().id(2L).title("Bases de Datos").build();

        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepo.findById(2L)).thenReturn(Optional.of(course));
        when(assessmentRepo.save(any())).thenAnswer(inv -> {
            Assessment a = inv.getArgument(0);
            a.setId(3L);
            return a;
        });

        var res = service.create(req);

        assertThat(res.id()).isEqualTo(3L);
        assertThat(res.type()).isEqualTo("EXAM");
        assertThat(res.score()).isEqualTo("95");
        verify(assessmentRepo).save(any(Assessment.class));
    }

    @Test
    void shouldThrowWhenCourseNotFound() {
        Student student = Student.builder().id(1L).build();
        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.create(new AssessmentCreateRequest(1L, 99L, "QUIZ", "70")))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }
}
