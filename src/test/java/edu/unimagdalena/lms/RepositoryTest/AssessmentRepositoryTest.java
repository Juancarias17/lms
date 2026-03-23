package edu.unimagdalena.lms.RepositoryTest;

import edu.unimagdalena.lms.Repositories.AssessmentRepository;
import edu.unimagdalena.lms.Repositories.CourseRepository;
import edu.unimagdalena.lms.Repositories.StudentRepository;
import edu.unimagdalena.lms.TestcontainersConfiguration;
import edu.unimagdalena.lms.entites.Assessment;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Student;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import(TestcontainersConfiguration.class)
public class AssessmentRepositoryTest {

    @Autowired
    AssessmentRepository assessmentRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Test
    @DisplayName("Assessment: Encuentra evaluaciones por estudiante")
    void shouldFindByStudent() {
        Student student = studentRepository.save(Student.builder().fullName("Sofía Herrera").build());
        Course course = courseRepository.save(Course.builder().title("Estructura de datos").status("ACTIVE").active(true).build());

        assessmentRepository.save(Assessment.builder().student(student).course(course).type("Quiz").score("85").build());
        assessmentRepository.save(Assessment.builder().student(student).course(course).type("Examen").score("90").build());

        List<Assessment> assessments = assessmentRepository.findByStudent(student);

        assertThat(assessments).hasSize(2);
        assertThat(assessments).extracting(Assessment::getType).contains("Quiz", "Examen");
    }

    @Test
    @DisplayName("Assessment: Encuentra evaluaciones por curso")
    void shouldFindByCourse() {
        Course course = courseRepository.save(Course.builder().title("Estructura de datos").status("ACTIVE").active(true).build());
        Student s1 = studentRepository.save(Student.builder().fullName("Carlos Díaz").build());
        Student s2 = studentRepository.save(Student.builder().fullName("María López").build());

        assessmentRepository.save(Assessment.builder().student(s1).course(course).type("Quiz").score("70").build());
        assessmentRepository.save(Assessment.builder().student(s2).course(course).type("Quiz").score("80").build());

        List<Assessment> assessments = assessmentRepository.findByCourse(course);

        assertThat(assessments).hasSize(2);
        assertThat(assessments).extracting(Assessment::getScore).contains("70", "80");
    }

    @Test
    @DisplayName("Assessment: Encuentra evaluaciones por tipo")
    void shouldFindByType() {
        Student student = studentRepository.save(Student.builder().fullName("Jorge Castro").build());
        Course course = courseRepository.save(Course.builder().title("Estructura de datos").status("ACTIVE").active(true).build());

        assessmentRepository.save(Assessment.builder().student(student).course(course).type("Examen").score("95").build());
        assessmentRepository.save(Assessment.builder().student(student).course(course).type("Quiz").score("60").build());
        assessmentRepository.save(Assessment.builder().student(student).course(course).type("Examen").score("88").build());

        List<Assessment> Examens = assessmentRepository.findByType("Examen");

        assertThat(Examens).hasSize(2);
        assertThat(Examens).extracting(Assessment::getType).containsOnly("Examen");
    }
}