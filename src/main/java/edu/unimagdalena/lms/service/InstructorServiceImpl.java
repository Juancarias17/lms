package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.InstructorDtos.*;
import edu.unimagdalena.lms.entites.Instructor;
import edu.unimagdalena.lms.Repositories.InstructorRepository;
import edu.unimagdalena.lms.service.mapper.InstructorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor @Transactional
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepo;

    @Override
    public InstructorResponse create(InstructorCreateRequest req) {
        Instructor i = InstructorMapper.toEntity(req);
        return InstructorMapper.toResponse(instructorRepo.save(i));
    }

    @Override @Transactional(readOnly = true)
    public InstructorResponse get(Long id) {
        return instructorRepo.findById(id).map(InstructorMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Instructor %d not found".formatted(id)));
    }

    @Override
    public void delete(Long id) { instructorRepo.deleteById(id); }
}
