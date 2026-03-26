package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.LessonDtos.*;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Lesson;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class LessonMapperTest {

    private final LessonMapper mapper = Mappers.getMapper(LessonMapper.class);

    @Test
    void toEntity_shouldMapCreate() {
        Lesson l = mapper.toEntity(new LessonCreateRequest(1L, "Introducción", 1));
        assertThat(l.getTitle()).isEqualTo("Introducción");
        assertThat(l.getOrderIndex()).isEqualTo(1);
    }

    @Test
    void toResponse_shouldMapEntity() {
        Course course = Course.builder().id(2L).build();
        var l = Lesson.builder().id(8L).course(course).title("Introducción").orderIndex(1).build();
        LessonResponse dto = mapper.toResponse(l);
        assertThat(dto.id()).isEqualTo(8L);
        assertThat(dto.courseId()).isEqualTo(2L);
        assertThat(dto.title()).isEqualTo("Introducción");
    }
}
