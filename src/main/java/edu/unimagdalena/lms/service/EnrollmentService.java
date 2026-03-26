package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.EnrollmentDtos.*;

public interface EnrollmentService {
    EnrollmentResponse create(EnrollmentCreateRequest req);
    EnrollmentResponse get(Long id);
    void delete(Long id);
}
