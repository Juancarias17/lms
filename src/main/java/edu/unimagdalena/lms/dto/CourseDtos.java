package edu.unimagdalena.lms.dto;

import java.io.Serializable;

public class CourseDtos {

    public record CourseCreateRequest(
            String title,
            String status,
            Boolean active,
            Long instructorId) implements Serializable {}

    public record CourseResponse(
            Long id,
            String title,
            String status,
            Boolean active,
            Long instructorId) implements Serializable {}
}