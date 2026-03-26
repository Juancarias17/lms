package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.InstructorProfileDtos.InstructorProfileCreateRequest;
import edu.unimagdalena.lms.dto.InstructorProfileDtos.InstructorProfileResponse;
import edu.unimagdalena.lms.entites.InstructorProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IInstructorProfileMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "instructor", ignore = true) // se setea en el servicio
    InstructorProfile toEntity(InstructorProfileCreateRequest req);

    @Mapping(target = "instructorId", source = "instructor.id")
    InstructorProfileResponse toResponse(InstructorProfile p);
}
