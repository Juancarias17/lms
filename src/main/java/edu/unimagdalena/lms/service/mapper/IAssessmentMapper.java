package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.AssessmentDtos.AssessmentCreateRequest;
import edu.unimagdalena.lms.dto.AssessmentDtos.AssessmentResponse;
import edu.unimagdalena.lms.entites.Assessment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAssessmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student", ignore = true) // se setea en el servicio
    @Mapping(target = "course", ignore = true)  // se setea en el servicio
    @Mapping(target = "takenAt", ignore = true)
    Assessment toEntity(AssessmentCreateRequest req);

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "courseId", source = "course.id")
    AssessmentResponse toResponse(Assessment a);
}
