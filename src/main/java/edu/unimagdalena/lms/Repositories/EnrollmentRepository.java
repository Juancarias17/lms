package edu.unimagdalena.lms.Repositories;

import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Enrollment;
import edu.unimagdalena.lms.entites.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudent(Student student);
    List<Enrollment> findByCourse(Course course);
    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);
}
