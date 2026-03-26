package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.CourseDtos.CourseCreateRequest;
import edu.unimagdalena.lms.dto.CourseDtos.CourseResponse;
import edu.unimagdalena.lms.entites.Course;
import org.mapstruct.Mapper;

@Mapper
public class CourseMapper {

    public static Course toEntity(CourseCreateRequest req) {
        return Course.builder()
                .title(req.title())
                .status(req.status())
                .active(req.active())
                .build();
    }

    public static CourseResponse toResponse(Course c) {
        return new CourseResponse(
                c.getId(),
                c.getTitle(),
                c.getStatus(),
                c.getActive(),
                c.getInstructor() != null ? c.getInstructor().getId() : null
        );
    }
}
