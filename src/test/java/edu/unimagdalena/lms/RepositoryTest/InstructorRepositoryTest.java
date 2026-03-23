package edu.unimagdalena.lms.RepositoryTest;

import edu.unimagdalena.lms.Repositories.InstructorRepository;
import edu.unimagdalena.lms.TestcontainersConfiguration;
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
public class InstructorRepositoryTest {

    @Autowired
    InstructorRepository instructorRepository;

    @Test
    @DisplayName("Instructor: Encuentra por email")
    void shouldFindByEmail() {
        instructorRepository.save(Instructor.builder().fullName("Laura Reyes").email("lreyes@unimagdalena.edu.co").build());

        assertThat(instructorRepository.findByEmail("lreyes@unimagdalena.edu.co")).isPresent();
        assertThat(instructorRepository.findByEmail("lreyes@unimagdalena.edu.co").get().getFullName())
                .isEqualTo("Laura Reyes");
    }

    @Test
    @DisplayName("Instructor: Encuentra por nombre completo")
    void shouldFindByFullName() {
        instructorRepository.save(Instructor.builder().fullName("Roberto Silva").email("rsilva@unimagdalena.edu.co").build());

        assertThat(instructorRepository.findByFullName("Roberto Silva")).isPresent();
        assertThat(instructorRepository.findByFullName("Roberto Silva").get().getEmail())
                .isEqualTo("rsilva@unimagdalena.edu.co");
    }

    @Test
    @DisplayName("Instructor: Encuentra por keyword en nombre")
    void shouldFindByFullNameContainingIgnoreCase() {
        instructorRepository.save(Instructor.builder().fullName("Andrea Mora").email("amora@unimagdalena.edu.co").build());
        instructorRepository.save(Instructor.builder().fullName("Luis Morales").email("lmorales@unimagdalena.edu.co").build());
        instructorRepository.save(Instructor.builder().fullName("Carmen López").email("clopez@unimagdalena.edu.co").build());

        List<Instructor> result = instructorRepository.findByFullNameContainingIgnoreCase("mora");

        assertThat(result).hasSize(2);
        assertThat(result).extracting(Instructor::getFullName).contains("Andrea Mora", "Luis Morales");
    }
}
