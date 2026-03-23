package edu.unimagdalena.lms.RepositoryTest;

import edu.unimagdalena.lms.Repositories.CourseRepository;
import edu.unimagdalena.lms.Repositories.EnrollmentRepository;
import edu.unimagdalena.lms.Repositories.StudentRepository;
import edu.unimagdalena.lms.TestcontainersConfiguration;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Enrollment;
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
public class EnrollmentRepositoryTest {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Test
    @DisplayName("Enrollment: Encuentra inscripciones por estudiante")
    void shouldFindByStudent() {
        Student student = studentRepository.save(Student.builder().fullName("Ana Torres").build());
        Course course1 = courseRepository.save(Course.builder().title("Estructura de datos").status("ACTIVE").active(true).build());
        Course course2 = courseRepository.save(Course.builder().title("Base de datos").status("ACTIVE").active(true).build());

        enrollmentRepository.save(Enrollment.builder().student(student).course(course1).status("ENROLLED").build());
        enrollmentRepository.save(Enrollment.builder().student(student).course(course2).status("ENROLLED").build());

        List<Enrollment> enrollments = enrollmentRepository.findByStudent(student);

        assertThat(enrollments).hasSize(2);
        assertThat(enrollments).extracting(e -> e.getCourse().getTitle()).contains("Estructura de datos", "Base de datos");
    }

    @Test
    @DisplayName("Enrollment: Encuentra inscripciones por curso")
    void shouldFindByCourse() {
        Course course = courseRepository.save(Course.builder().title("Estructura de datos").status("ACTIVE").active(true).build());
        Student s1 = studentRepository.save(Student.builder().fullName("Luis Martínez").build());
        Student s2 = studentRepository.save(Student.builder().fullName("Paula Gómez").build());

        enrollmentRepository.save(Enrollment.builder().student(s1).course(course).status("ENROLLED").build());
        enrollmentRepository.save(Enrollment.builder().student(s2).course(course).status("ENROLLED").build());

        List<Enrollment> enrollments = enrollmentRepository.findByCourse(course);

        assertThat(enrollments).hasSize(2);
        assertThat(enrollments).extracting(e -> e.getStudent().getFullName()).contains("Luis Martínez", "Paula Gómez");
    }

    @Test
    @DisplayName("Enrollment: Encuentra inscripción por estudiante y curso")
    void shouldFindByStudentAndCourse() {
        Student student = studentRepository.save(Student.builder().fullName("Pedro Ramírez").build());
        Course course = courseRepository.save(Course.builder().title("Redes").status("ACTIVE").active(true).build());

        enrollmentRepository.save(Enrollment.builder().student(student).course(course).status("ENROLLED").build());

        assertThat(enrollmentRepository.findByStudentAndCourse(student, course)).isPresent();
        assertThat(enrollmentRepository.findByStudentAndCourse(student, course).get().getStatus()).isEqualTo("ENROLLED");
    }
}
