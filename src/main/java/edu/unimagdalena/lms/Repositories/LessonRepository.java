package edu.unimagdalena.lms.Repositories;

import edu.unimagdalena.lms.entites.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
