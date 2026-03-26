package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.EnrollmentDtos.*;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Enrollment;
import edu.unimagdalena.lms.entites.Student;
import edu.unimagdalena.lms.Repositories.CourseRepository;
import edu.unimagdalena.lms.Repositories.EnrollmentRepository;
import edu.unimagdalena.lms.Repositories.StudentRepository;
import edu.unimagdalena.lms.service.mapper.EnrollmentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceImplTest {

    @Mock EnrollmentRepository enrollmentRepo;
    @Mock StudentRepository studentRepo;
    @Mock CourseRepository courseRepo;

    @InjectMocks
    EnrollmentServiceImpl service;

    @Spy
    private EnrollmentMapper mapper = Mappers.getMapper(EnrollmentMapper.class);

    @Test
    void shouldCreateAndReturnResponseDto() {
        var req = new EnrollmentCreateRequest(1L, 2L, "ENROLLED");
        Student student = Student.builder().id(1L).fullName("Ana Torres").build();
        Course course = Course.builder().id(2L).title("Spring Boot").build();

        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
        when(courseRepo.findById(2L)).thenReturn(Optional.of(course));
        when(enrollmentRepo.save(any())).thenAnswer(inv -> {
            Enrollment e = inv.getArgument(0);
            e.setId(9L);
            return e;
        });

        var res = service.create(req);

        assertThat(res.id()).isEqualTo(9L);
        assertThat(res.studentId()).isEqualTo(1L);
        assertThat(res.courseId()).isEqualTo(2L);
        verify(enrollmentRepo).save(any(Enrollment.class));
    }

    @Test
    void shouldThrowWhenStudentNotFound() {
        when(studentRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.create(new EnrollmentCreateRequest(99L, 1L, "ENROLLED")))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }
}
