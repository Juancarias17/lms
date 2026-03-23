package edu.unimagdalena.lms.Repositories;

import edu.unimagdalena.lms.entites.Assessment;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {

    List<Assessment> findByStudent(Student student);
    List<Assessment> findByCourse(Course course);
    List<Assessment> findByType(String type);
}
