package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.InstructorDtos.InstructorCreateRequest;
import edu.unimagdalena.lms.dto.InstructorDtos.InstructorResponse;
import edu.unimagdalena.lms.entites.Instructor;
import org.mapstruct.Mapper;

@Mapper
public class InstructorMapper {

    public static Instructor toEntity(InstructorCreateRequest req) {
        return Instructor.builder()
                .fullName(req.fullName())
                .email(req.email())
                .build();
    }

    public static InstructorResponse toResponse(Instructor i) {
        return new InstructorResponse(
                i.getId(),
                i.getFullName(),
                i.getEmail()
        );
    }
}
