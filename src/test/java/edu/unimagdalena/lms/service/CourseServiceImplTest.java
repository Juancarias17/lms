package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.CourseDtos.*;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Instructor;
import edu.unimagdalena.lms.Repositories.CourseRepository;
import edu.unimagdalena.lms.Repositories.InstructorRepository;
import edu.unimagdalena.lms.service.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    CourseRepository courseRepo;

    @Mock
    InstructorRepository instructorRepo;

    @InjectMocks
    CourseServiceImpl service;

    @Spy
    private CourseMapper mapper = Mappers.getMapper(CourseMapper.class);

    @Test
    void shouldCreateAndReturnResponseDto() {
        var req = new CourseCreateRequest("Spring Boot", "ACTIVE", true, 1L);
        Instructor instructor = Instructor.builder().id(1L).fullName("Carlos Ruiz").email("carlos@uni.edu").build();

        when(instructorRepo.findById(1L)).thenReturn(Optional.of(instructor));
        when(courseRepo.save(any())).thenAnswer(inv -> {
            Course c = inv.getArgument(0);
            c.setId(5L);
            return c;
        });

        var res = service.create(1L, req);

        assertThat(res.id()).isEqualTo(5L);
        assertThat(res.title()).isEqualTo("Spring Boot");
        assertThat(res.instructorId()).isEqualTo(1L);
        verify(courseRepo).save(any(Course.class));
    }

    @Test
    void shouldThrowWhenInstructorNotFound() {
        when(instructorRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.create(99L, new CourseCreateRequest("Test", "ACTIVE", true, 99L)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }
}
