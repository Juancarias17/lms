package edu.unimagdalena.lms.dto;

import java.io.Serializable;

public class InstructorDtos {

    public record InstructorCreateRequest(
            String fullName,
            String email) implements Serializable {}

    public record InstructorResponse(
            Long id,
            String fullName,
            String email) implements Serializable {}
}