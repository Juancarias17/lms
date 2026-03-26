package edu.unimagdalena.lms.dto;

import java.io.Serializable;

public class InstructorProfileDtos {

    public record InstructorProfileCreateRequest(
            Long instructorId,
            String phone,
            String bio) implements Serializable {}

    public record InstructorProfileResponse(
            Long id,
            Long instructorId,
            String phone,
            String bio) implements Serializable {}
}