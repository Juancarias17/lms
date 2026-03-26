package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.AssessmentDtos.*;
import edu.unimagdalena.lms.entites.Assessment;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Student;
import edu.unimagdalena.lms.Repositories.AssessmentRepository;
import edu.unimagdalena.lms.Repositories.CourseRepository;
import edu.unimagdalena.lms.Repositories.StudentRepository;
import edu.unimagdalena.lms.service.mapper.AssessmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor @Transactional
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    @Override
    public AssessmentResponse create(AssessmentCreateRequest req) {
        Student student = studentRepo.findById(req.studentId())
                .orElseThrow(() -> new RuntimeException("Student %d not found".formatted(req.studentId())));
        Course course = courseRepo.findById(req.courseId())
                .orElseThrow(() -> new RuntimeException("Course %d not found".formatted(req.courseId())));
        Assessment a = AssessmentMapper.toEntity(req);
        a.setStudent(student);
        a.setCourse(course);
        a.setTakenAt(Instant.now());
        return AssessmentMapper.toResponse(assessmentRepo.save(a));
    }

    @Override @Transactional(readOnly = true)
    public AssessmentResponse get(Long id) {
        return assessmentRepo.findById(id).map(AssessmentMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Assessment %d not found".formatted(id)));
    }

    @Override
    public void delete(Long id) { assessmentRepo.deleteById(id); }
}
