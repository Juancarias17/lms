package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.InstructorDtos.*;
import edu.unimagdalena.lms.entites.Instructor;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class InstructorMapperTest {

    private final InstructorMapper mapper = Mappers.getMapper(InstructorMapper.class);

    @Test
    void toEntity_shouldMapCreate() {
        Instructor i = mapper.toEntity(new InstructorCreateRequest("Carlos Ruiz", "carlos@uni.edu"));
        assertThat(i.getFullName()).isEqualTo("Carlos Ruiz");
        assertThat(i.getEmail()).isEqualTo("carlos@uni.edu");
    }

    @Test
    void toResponse_shouldMapEntity() {
        var i = Instructor.builder().id(4L).fullName("Carlos Ruiz").email("carlos@uni.edu").build();
        InstructorResponse dto = mapper.toResponse(i);
        assertThat(dto.id()).isEqualTo(4L);
        assertThat(dto.email()).isEqualTo("carlos@uni.edu");
    }
}
