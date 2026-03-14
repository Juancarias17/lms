package edu.unimagdalena.lms;

import edu.unimagdalena.lms.Repositories.StudentRepository;
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
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepo;

    @Test
    @DisplayName("Student: Encuentra por nombre, lista de nombres y como empieza")
    void ShouldFindByNameByNameInListAndByNameStartingWith() {
        studentRepo.save(Student.builder().fullName("Felipe Andres Quintero").build());
        studentRepo.save(Student.builder().fullName("Juan Camilo Arias").build());
        studentRepo.save(Student.builder().fullName("Mario Gonzales").build());

        assertThat(studentRepo.findByFullName("Mario Gonzales")).isPresent();
        assertThat(studentRepo.findByFullNameIn(List.of("Felipe Andres Quintero", "Juan Camilo Arias", "Mario Gonzales")))
                .extracting(Student::getFullName).contains("Juan Camilo Arias", "Mario Gonzales");
        assertThat(studentRepo.findByFullNameStartingWithIgnoreCase("J")).isPresent();
    }

}
