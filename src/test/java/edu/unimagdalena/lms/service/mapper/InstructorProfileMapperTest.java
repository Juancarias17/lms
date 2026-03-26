package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.InstructorProfileDtos.*;
import edu.unimagdalena.lms.entites.Instructor;
import edu.unimagdalena.lms.entites.InstructorProfile;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class InstructorProfileMapperTest {

    private final InstructorProfileMapper mapper = Mappers.getMapper(InstructorProfileMapper.class);

    @Test
    void toEntity_shouldMapCreate() {
        InstructorProfile p = mapper.toEntity(new InstructorProfileCreateRequest(1L, "3001234567", "Experto en Java"));
        assertThat(p.getPhone()).isEqualTo("3001234567");
        assertThat(p.getBio()).isEqualTo("Experto en Java");
    }

    @Test
    void toResponse_shouldMapEntity() {
        Instructor instructor = Instructor.builder().id(1L).build();
        var p = InstructorProfile.builder().id(6L).instructor(instructor).phone("3001234567").bio("Experto en Java").build();
        InstructorProfileResponse dto = mapper.toResponse(p);
        assertThat(dto.id()).isEqualTo(6L);
        assertThat(dto.instructorId()).isEqualTo(1L);
        assertThat(dto.bio()).isEqualTo("Experto en Java");
    }
}
