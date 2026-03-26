package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.LessonDtos.*;

public interface LessonService {
    LessonResponse create(LessonCreateRequest req);
    LessonResponse get(Long id);
    void delete(Long id);
}
