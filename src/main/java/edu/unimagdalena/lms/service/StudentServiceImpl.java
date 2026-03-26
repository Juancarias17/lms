package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.StudentDtos.*;
import edu.unimagdalena.lms.entites.Student;
import edu.unimagdalena.lms.Repositories.StudentRepository;
import edu.unimagdalena.lms.service.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor @Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepo;

    @Override
    public StudentResponse create(StudentCreateRequest req) {
        Student s = StudentMapper.toEntity(req);
        return StudentMapper.toResponse(studentRepo.save(s));
    }

    @Override @Transactional(readOnly = true)
    public StudentResponse get(Long id) {
        return studentRepo.findById(id).map(StudentMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Student %d not found".formatted(id)));
    }

    @Override
    public void delete(Long id) { studentRepo.deleteById(id); }
}
