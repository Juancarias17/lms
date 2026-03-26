package edu.unimagdalena.lms.service.mapper;

import edu.unimagdalena.lms.dto.EnrollmentDtos.EnrollmentCreateRequest;
import edu.unimagdalena.lms.dto.EnrollmentDtos.EnrollmentResponse;
import edu.unimagdalena.lms.entites.Enrollment;
import org.mapstruct.Mapper;

@Mapper
public class EnrollmentMapper {

    public static Enrollment toEntity(EnrollmentCreateRequest req) {
        return Enrollment.builder()
                .status(req.status())
                .build();
    }

    public static EnrollmentResponse toResponse(Enrollment e) {
        return new EnrollmentResponse(
                e.getId(),
                e.getStudent() != null ? e.getStudent().getId() : null,
                e.getCourse() != null ? e.getCourse().getId() : null,
                e.getStatus(),
                e.getEnrolledAt()
        );
    }
}
