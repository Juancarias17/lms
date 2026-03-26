package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.StudentDtos.*;

public interface StudentService {
    StudentResponse create(StudentCreateRequest req);
    StudentResponse get(Long id);
    void delete(Long id);
}
