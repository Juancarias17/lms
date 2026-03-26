package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.CourseDtos.*;

public interface CourseService {
    CourseResponse create(Long instructorId, CourseCreateRequest req);
    CourseResponse get(Long id);
    void delete(Long id);
}
