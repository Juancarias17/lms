package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.InstructorProfileDtos.*;

public interface InstructorProfileService {
    InstructorProfileResponse create(InstructorProfileCreateRequest req);
    InstructorProfileResponse get(Long id);
    void delete(Long id);
}
