package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.LessonDtos.LessonCreateRequest;
import edu.unimagdalena.lms.dto.LessonDtos.LessonResponse;
import edu.unimagdalena.lms.entites.Lesson;
import org.mapstruct.Mapper;

@Mapper
public class LessonMapper {

    public static Lesson toEntity(LessonCreateRequest req) {
        return Lesson.builder()
                .title(req.title())
                .orderIndex(req.orderIndex())
                .build();
    }

    public static LessonResponse toResponse(Lesson l) {
        return new LessonResponse(
                l.getId(),
                l.getCourse() != null ? l.getCourse().getId() : null,
                l.getTitle(),
                l.getOrderIndex()
        );
    }
}
