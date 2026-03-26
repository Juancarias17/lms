package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.CourseDtos.*;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Instructor;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class CourseMapperTest {

    private final CourseMapper mapper = Mappers.getMapper(CourseMapper.class);

    @Test
    void toEntity_shouldMapCreate() {
        Course c = mapper.toEntity(new CourseCreateRequest("Spring Boot", "ACTIVE", true, null));
        assertThat(c.getTitle()).isEqualTo("Spring Boot");
        assertThat(c.getStatus()).isEqualTo("ACTIVE");
    }

    @Test
    void toResponse_shouldMapEntity() {
        Instructor instructor = Instructor.builder().id(2L).build();
        var c = Course.builder().id(10L).title("Spring Boot").status("ACTIVE").active(true).instructor(instructor).build();
        CourseResponse dto = mapper.toResponse(c);
        assertThat(dto.id()).isEqualTo(10L);
        assertThat(dto.instructorId()).isEqualTo(2L);
    }
}
