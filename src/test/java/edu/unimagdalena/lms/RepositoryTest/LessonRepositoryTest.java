package edu.unimagdalena.lms.RepositoryTest;

import edu.unimagdalena.lms.Repositories.CourseRepository;
import edu.unimagdalena.lms.Repositories.LessonRepository;
import edu.unimagdalena.lms.TestcontainersConfiguration;
import edu.unimagdalena.lms.entites.Course;
import edu.unimagdalena.lms.entites.Lesson;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import(TestcontainersConfiguration.class)
public class LessonRepositoryTest {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    CourseRepository courseRepository;

    @Test
    @DisplayName("Lesson: Encuentra lecciones por curso")
    void shouldFindByCourse() {
        Course course = courseRepository.save(Course.builder().title("Docker").status("ACTIVE").active(true).build());

        lessonRepository.save(Lesson.builder().course(course).title("Introducción a Docker").orderIndex(1).build());
        lessonRepository.save(Lesson.builder().course(course).title("Contenedores").orderIndex(2).build());

        List<Lesson> lessons = lessonRepository.findByCourse(course);

        assertThat(lessons).hasSize(2);
        assertThat(lessons).extracting(Lesson::getTitle).contains("Introducción a Docker", "Contenedores");
    }

    @Test
    @DisplayName("Lesson: Encuentra lección por título")
    void shouldFindByTitle() {
        Course course = courseRepository.save(Course.builder().title("Programación web").status("ACTIVE").active(true).build());
        lessonRepository.save(Lesson.builder().course(course).title("Spring Boot").orderIndex(1).build());

        assertThat(lessonRepository.findByTitle("Spring Boot")).isPresent();
        assertThat(lessonRepository.findByTitle("Spring Boot").get().getOrderIndex()).isEqualTo(1);
    }

    @Test
    @DisplayName("Lesson: Encuentra lecciones de un curso ordenadas por índice ascendente")
    void shouldFindByCourseOrderByOrderIndexAsc() {
        Course course = courseRepository.save(Course.builder().title("Git y GitHub").status("ACTIVE").active(true).build());

        lessonRepository.save(Lesson.builder().course(course).title("Ramas").orderIndex(3).build());
        lessonRepository.save(Lesson.builder().course(course).title("Instalación").orderIndex(1).build());
        lessonRepository.save(Lesson.builder().course(course).title("Primer Commit").orderIndex(2).build());

        List<Lesson> ordered = lessonRepository.findByCourseOrderByOrderIndexAsc(course);

        assertThat(ordered).hasSize(3);
        assertThat(ordered).extracting(Lesson::getOrderIndex).containsExactly(1, 2, 3);
        assertThat(ordered.get(0).getTitle()).isEqualTo("Instalación");
    }
}