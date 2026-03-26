package edu.unimagdalena.lms.dto;

import java.io.Serializable;

public class StudentDtos {
    public record StudentCreateRequest(String fullName, String email) implements Serializable {}
    public record StudentResponse(Long id, String fullName, String email) implements Serializable {}
}
