package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.InstructorProfileDtos.InstructorProfileCreateRequest;
import edu.unimagdalena.lms.dto.InstructorProfileDtos.InstructorProfileResponse;
import edu.unimagdalena.lms.entites.InstructorProfile;
import org.mapstruct.Mapper;

@Mapper
public class InstructorProfileMapper {

    public static InstructorProfile toEntity(InstructorProfileCreateRequest req) {
        return InstructorProfile.builder()
                .phone(req.phone())
                .bio(req.bio())
                .build();
    }

    public static InstructorProfileResponse toResponse(InstructorProfile p) {
        return new InstructorProfileResponse(
                p.getId(),
                p.getInstructor() != null ? p.getInstructor().getId() : null,
                p.getPhone(),
                p.getBio()
        );
    }
}
