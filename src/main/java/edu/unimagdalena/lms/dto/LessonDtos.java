package edu.unimagdalena.lms.dto;

import java.io.Serializable;

public class LessonDtos {

    public record LessonCreateRequest(
            Long courseId,
            String title,
            int orderIndex) implements Serializable {}

    public record LessonResponse(
            Long id,
            Long courseId,
            String title,
            int orderIndex) implements Serializable {}
}