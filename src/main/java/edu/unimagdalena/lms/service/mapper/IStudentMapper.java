package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.StudentDtos.StudentCreateRequest;
import edu.unimagdalena.lms.dto.StudentDtos.StudentResponse;
import edu.unimagdalena.lms.entites.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IStudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "assessments", ignore = true)
    @Mapping(target = "enrollments", ignore = true)
    Student toEntity(StudentCreateRequest req);

    StudentResponse toResponse(Student s);
}