package edu.unimagdalena.lms.Repositories;

import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByTitle(String title);
    List<Course> findByStatus(String status);
    List<Course> findByInstructor(Instructor instructor);
}
