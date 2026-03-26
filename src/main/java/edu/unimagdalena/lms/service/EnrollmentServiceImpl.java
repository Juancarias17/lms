package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.EnrollmentDtos.*;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Enrollment;
import edu.unimagdalena.lms.entites.Student;
import edu.unimagdalena.lms.Repositories.CourseRepository;
import edu.unimagdalena.lms.Repositories.EnrollmentRepository;
import edu.unimagdalena.lms.Repositories.StudentRepository;
import edu.unimagdalena.lms.service.mapper.EnrollmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor @Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    @Override
    public EnrollmentResponse create(EnrollmentCreateRequest req) {
        Student student = studentRepo.findById(req.studentId())
                .orElseThrow(() -> new RuntimeException("Student %d not found".formatted(req.studentId())));
        Course course = courseRepo.findById(req.courseId())
                .orElseThrow(() -> new RuntimeException("Course %d not found".formatted(req.courseId())));
        Enrollment e = EnrollmentMapper.toEntity(req);
        e.setStudent(student);
        e.setCourse(course);
        e.setEnrolledAt(Instant.now());
        return EnrollmentMapper.toResponse(enrollmentRepo.save(e));
    }

    @Override @Transactional(readOnly = true)
    public EnrollmentResponse get(Long id) {
        return enrollmentRepo.findById(id).map(EnrollmentMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Enrollment %d not found".formatted(id)));
    }

    @Override
    public void delete(Long id) { enrollmentRepo.deleteById(id); }
}
