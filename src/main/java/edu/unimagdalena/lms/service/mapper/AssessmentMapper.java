package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.AssessmentDtos.AssessmentCreateRequest;
import edu.unimagdalena.lms.dto.AssessmentDtos.AssessmentResponse;
import edu.unimagdalena.lms.entites.Assessment;
import org.mapstruct.Mapper;

@Mapper
public class AssessmentMapper {

    public static Assessment toEntity(AssessmentCreateRequest req) {
        return Assessment.builder()
                .type(req.type())
                .score(req.score())
                .build();
    }

    public static AssessmentResponse toResponse(Assessment a) {
        return new AssessmentResponse(
                a.getId(),
                a.getStudent() != null ? a.getStudent().getId() : null,
                a.getCourse() != null ? a.getCourse().getId() : null,
                a.getType(),
                a.getScore(),
                a.getTakenAt()
        );
    }
}
