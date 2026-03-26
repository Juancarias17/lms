package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.CourseDtos.CourseCreateRequest;
import edu.unimagdalena.lms.dto.CourseDtos.CourseResponse;
import edu.unimagdalena.lms.entites.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICourseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "instructor", ignore = true) // se setea en el servicio
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "uploadedAt", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    @Mapping(target = "assessments", ignore = true)
    @Mapping(target = "lessons", ignore = true)
    Course toEntity(CourseCreateRequest req);

    @Mapping(target = "instructorId", source = "instructor.id")
    CourseResponse toResponse(Course c);
}
