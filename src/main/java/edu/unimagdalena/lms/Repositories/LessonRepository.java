package edu.unimagdalena.lms.Repositories;

import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByCourse(Course course);
    Optional<Lesson> findByTitle(String title);
    List<Lesson> findByCourseOrderByOrderIndexAsc(Course course);
}
