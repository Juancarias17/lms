package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.EnrollmentDtos.EnrollmentCreateRequest;
import edu.unimagdalena.lms.dto.EnrollmentDtos.EnrollmentResponse;
import edu.unimagdalena.lms.entites.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IEnrollmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student", ignore = true) // se setea en el servicio
    @Mapping(target = "course", ignore = true)  // se setea en el servicio
    @Mapping(target = "enrolledAt", ignore = true)
    Enrollment toEntity(EnrollmentCreateRequest req);

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "courseId", source = "course.id")
    EnrollmentResponse toResponse(Enrollment e);
}
