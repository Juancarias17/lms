package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.EnrollmentDtos.*;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Enrollment;
import edu.unimagdalena.lms.entites.Student;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class EnrollmentMapperTest {

    private final EnrollmentMapper mapper = Mappers.getMapper(EnrollmentMapper.class);

    @Test
    void toEntity_shouldMapCreate() {
        Enrollment e = mapper.toEntity(new EnrollmentCreateRequest(1L, 2L, "ENROLLED"));
        assertThat(e.getStatus()).isEqualTo("ENROLLED");
    }

    @Test
    void toResponse_shouldMapEntity() {
        Student student = Student.builder().id(1L).build();
        Course course = Course.builder().id(2L).build();
        var e = Enrollment.builder().id(7L).student(student).course(course).status("ENROLLED").build();
        EnrollmentResponse dto = mapper.toResponse(e);
        assertThat(dto.id()).isEqualTo(7L);
        assertThat(dto.studentId()).isEqualTo(1L);
        assertThat(dto.courseId()).isEqualTo(2L);
    }
}
