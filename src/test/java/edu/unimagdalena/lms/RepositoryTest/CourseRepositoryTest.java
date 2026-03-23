package edu.unimagdalena.lms.RepositoryTest;

import edu.unimagdalena.lms.Repositories.CourseRepository;
import edu.unimagdalena.lms.Repositories.InstructorRepository;
import edu.unimagdalena.lms.TestcontainersConfiguration;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Instructor;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import(TestcontainersConfiguration.class)
public class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Test
    @DisplayName("Course: Encuentra por título")
    void shouldFindByTitle() {
        courseRepository.save(Course.builder().title("Estructura de datos").status("ACTIVE").active(true).build());

        assertThat(courseRepository.findByTitle("Estructura de datos")).isPresent();
        assertThat(courseRepository.findByTitle("Estructura de datos").get().getTitle()).isEqualTo("Estructura de datos");
    }

    @Test
    @DisplayName("Course: Encuentra lista por status")
    void shouldFindByStatus() {
        courseRepository.save(Course.builder().title("Estructura de datos").status("ACTIVE").active(true).build());
        courseRepository.save(Course.builder().title("Base de datos").status("ACTIVE").active(true).build());
        courseRepository.save(Course.builder().title("Redes").status("INACTIVE").active(false).build());

        List<Course> activeCourses = courseRepository.findByStatus("ACTIVE");

        assertThat(activeCourses).hasSize(2);
        assertThat(activeCourses).extracting(Course::getTitle).contains("Estructura de datos", "Base de datos");
    }

    @Test
    @DisplayName("Course: Encuentra cursos por instructor")
    void shouldFindByInstructor() {
        Instructor instructor = instructorRepository.save(
                Instructor.builder().fullName("Carlos Ruiz").email("cruiz@unimagdalena.edu.co").build());

        courseRepository.save(Course.builder().title("Base de datos").status("ACTIVE").active(true).instructor(instructor).build());
        courseRepository.save(Course.builder().title("Redes").status("ACTIVE").active(true).instructor(instructor).build());

        List<Course> courses = courseRepository.findByInstructor(instructor);

        assertThat(courses).hasSize(2);
        assertThat(courses).extracting(Course::getTitle).contains("Base de datos", "Redes");
    }
}