package edu.unimagdalena.lms.service;

import edu.unimagdalena.lms.dto.LessonDtos.*;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Lesson;
import edu.unimagdalena.lms.Repositories.CourseRepository;
import edu.unimagdalena.lms.Repositories.LessonRepository;
import edu.unimagdalena.lms.service.mapper.LessonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor @Transactional
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepo;
    private final CourseRepository courseRepo;

    @Override
    public LessonResponse create(LessonCreateRequest req) {
        Course course = courseRepo.findById(req.courseId())
                .orElseThrow(() -> new RuntimeException("Course %d not found".formatted(req.courseId())));
        Lesson l = LessonMapper.toEntity(req);
        l.setCourse(course);
        return LessonMapper.toResponse(lessonRepo.save(l));
    }

    @Override @Transactional(readOnly = true)
    public LessonResponse get(Long id) {
        return lessonRepo.findById(id).map(LessonMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Lesson %d not found".formatted(id)));
    }

    @Override
    public void delete(Long id) { lessonRepo.deleteById(id); }
}
