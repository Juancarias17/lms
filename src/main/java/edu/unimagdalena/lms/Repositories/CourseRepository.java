package edu.unimagdalena.lms.Repositories;

import edu.unimagdalena.lms.entites.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
