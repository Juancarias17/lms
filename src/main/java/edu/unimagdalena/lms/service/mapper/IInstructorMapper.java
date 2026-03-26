package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.InstructorDtos.InstructorCreateRequest;
import edu.unimagdalena.lms.dto.InstructorDtos.InstructorResponse;
import edu.unimagdalena.lms.entites.Instructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IInstructorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "uploaded_at", ignore = true)
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "instructorProfile", ignore = true)
    Instructor toEntity(InstructorCreateRequest req);

    InstructorResponse toResponse(Instructor i);
}
