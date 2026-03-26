package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.InstructorDtos.*;

public interface InstructorService {
    InstructorResponse create(InstructorCreateRequest req);
    InstructorResponse get(Long id);
    void delete(Long id);
}
