package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.LessonDtos.*;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Lesson;
import edu.unimagdalena.lms.Repositories.CourseRepository;
import edu.unimagdalena.lms.Repositories.LessonRepository;
import edu.unimagdalena.lms.service.mapper.LessonMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LessonServiceImplTest {

    @Mock LessonRepository lessonRepo;
    @Mock CourseRepository courseRepo;

    @InjectMocks
    LessonServiceImpl service;

    @Spy
    private LessonMapper mapper = Mappers.getMapper(LessonMapper.class);

    @Test
    void shouldCreateAndReturnResponseDto() {
        var req = new LessonCreateRequest(2L, "Introducción a Spring", 1);
        Course course = Course.builder().id(2L).title("Spring Boot").build();

        when(courseRepo.findById(2L)).thenReturn(Optional.of(course));
        when(lessonRepo.save(any())).thenAnswer(inv -> {
            Lesson l = inv.getArgument(0);
            l.setId(8L);
            return l;
        });

        var res = service.create(req);

        assertThat(res.id()).isEqualTo(8L);
        assertThat(res.title()).isEqualTo("Introducción a Spring");
        assertThat(res.courseId()).isEqualTo(2L);
        verify(lessonRepo).save(any(Lesson.class));
    }

    @Test
    void shouldThrowWhenCourseNotFound() {
        when(courseRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.create(new LessonCreateRequest(99L, "Lección", 1)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("99");
    }
}
