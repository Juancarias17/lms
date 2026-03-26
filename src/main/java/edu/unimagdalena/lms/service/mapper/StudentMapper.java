package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.StudentDtos.StudentCreateRequest;
import edu.unimagdalena.lms.dto.StudentDtos.StudentResponse;
import edu.unimagdalena.lms.entites.Student;
import org.mapstruct.Mapper;

@Mapper
public class StudentMapper {

    public static Student toEntity(StudentCreateRequest req) {
        return Student.builder()
                .fullName(req.fullName())
                .email(req.email())
                .build();
    }

    public static StudentResponse toResponse(Student s) {
        return new StudentResponse(
                s.getId(),
                s.getFullName(),
                s.getEmail()
        );
    }
}
