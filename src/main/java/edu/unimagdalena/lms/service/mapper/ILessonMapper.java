package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.LessonDtos.LessonCreateRequest;
import edu.unimagdalena.lms.dto.LessonDtos.LessonResponse;
import edu.unimagdalena.lms.entites.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ILessonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true) // se setea en el servicio
    Lesson toEntity(LessonCreateRequest req);

    @Mapping(target = "courseId", source = "course.id")
    LessonResponse toResponse(Lesson l);
}
