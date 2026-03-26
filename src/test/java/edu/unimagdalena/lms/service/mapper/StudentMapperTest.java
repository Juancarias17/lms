package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.StudentDtos.*;
import edu.unimagdalena.lms.entites.Student;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class StudentMapperTest {

    private final StudentMapper mapper = Mappers.getMapper(StudentMapper.class);

    @Test
    void toEntity_shouldMapCreate() {
        Student s = mapper.toEntity(new StudentCreateRequest("Ana Gomez", "ana@uni.edu"));
        assertThat(s.getFullName()).isEqualTo("Ana Gomez");
        assertThat(s.getEmail()).isEqualTo("ana@uni.edu");
    }

    @Test
    void toResponse_shouldMapEntity() {
        var s = Student.builder().id(5L).fullName("Ana Gomez").email("ana@uni.edu").build();
        StudentResponse dto = mapper.toResponse(s);
        assertThat(dto.id()).isEqualTo(5L);
        assertThat(dto.fullName()).isEqualTo("Ana Gomez");
    }
}
