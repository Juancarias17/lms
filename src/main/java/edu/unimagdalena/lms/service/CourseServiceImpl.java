package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.CourseDtos.*;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Instructor;
import edu.unimagdalena.lms.Repositories.CourseRepository;
import edu.unimagdalena.lms.Repositories.InstructorRepository;
import edu.unimagdalena.lms.service.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor @Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepo;
    private final InstructorRepository instructorRepo;

    @Override
    public CourseResponse create(Long instructorId, CourseCreateRequest req) {
        Instructor instructor = instructorRepo.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor %d not found".formatted(instructorId)));
        Course c = CourseMapper.toEntity(req);
        c.setInstructor(instructor);
        return CourseMapper.toResponse(courseRepo.save(c));
    }

    @Override @Transactional(readOnly = true)
    public CourseResponse get(Long id) {
        return courseRepo.findById(id).map(CourseMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Course %d not found".formatted(id)));
    }

    @Override
    public void delete(Long id) { courseRepo.deleteById(id); }
}
