package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.AssessmentDtos.*;

public interface AssessmentService {
    AssessmentResponse create(AssessmentCreateRequest req);
    AssessmentResponse get(Long id);
    void delete(Long id);
}
