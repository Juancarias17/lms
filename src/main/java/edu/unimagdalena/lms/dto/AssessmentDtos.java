package edu.unimagdalena.lms.dto;

import java.io.Serializable;
import java.time.Instant;

public class AssessmentDtos {

    public record AssessmentCreateRequest(
            Long studentId,
            Long courseId,
            String type,
            String score) implements Serializable {}

    public record AssessmentResponse(
            Long id,
            Long studentId,
            Long courseId,
            String type,
            String score,
            Instant takenAt) implements Serializable {}
}