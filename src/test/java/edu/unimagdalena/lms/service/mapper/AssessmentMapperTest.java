package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.AssessmentDtos.*;
import edu.unimagdalena.lms.entites.Assessment;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Student;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class AssessmentMapperTest {

    private final AssessmentMapper mapper = Mappers.getMapper(AssessmentMapper.class);

    @Test
    void toEntity_shouldMapCreate() {
        Assessment a = mapper.toEntity(new AssessmentCreateRequest(1L, 2L, "EXAM", "90"));
        assertThat(a.getType()).isEqualTo("EXAM");
        assertThat(a.getScore()).isEqualTo("90");
    }

    @Test
    void toResponse_shouldMapEntity() {
        Student student = Student.builder().id(1L).build();
        Course course = Course.builder().id(2L).build();
        var a = Assessment.builder().id(3L).student(student).course(course).type("EXAM").score("90").build();
        AssessmentResponse dto = mapper.toResponse(a);
        assertThat(dto.id()).isEqualTo(3L);
        assertThat(dto.studentId()).isEqualTo(1L);
        assertThat(dto.score()).isEqualTo("90");
    }
}
