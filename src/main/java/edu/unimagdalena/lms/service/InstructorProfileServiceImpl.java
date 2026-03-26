package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.InstructorProfileDtos.*;
import edu.unimagdalena.lms.entites.Instructor;
import edu.unimagdalena.lms.entites.InstructorProfile;
import edu.unimagdalena.lms.Repositories.InstructorProfileRepository;
import edu.unimagdalena.lms.Repositories.InstructorRepository;
import edu.unimagdalena.lms.service.mapper.InstructorProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor @Transactional
public class InstructorProfileServiceImpl implements InstructorProfileService {

    private final InstructorProfileRepository instructorProfileRepo;
    private final InstructorRepository instructorRepo;

    @Override
    public InstructorProfileResponse create(InstructorProfileCreateRequest req) {
        Instructor instructor = instructorRepo.findById(req.instructorId())
                .orElseThrow(() -> new RuntimeException("Instructor %d not found".formatted(req.instructorId())));
        InstructorProfile p = InstructorProfileMapper.toEntity(req);
        p.setInstructor(instructor);
        return InstructorProfileMapper.toResponse(instructorProfileRepo.save(p));
    }

    @Override @Transactional(readOnly = true)
    public InstructorProfileResponse get(Long id) {
        return instructorProfileRepo.findById(id).map(InstructorProfileMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("InstructorProfile %d not found".formatted(id)));
    }

    @Override
    public void delete(Long id) { instructorProfileRepo.deleteById(id); }
}
