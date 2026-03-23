package edu.unimagdalena.lms.RepositoryTest;

import edu.unimagdalena.lms.Repositories.InstructorProfileRepository;
import edu.unimagdalena.lms.Repositories.InstructorRepository;
import edu.unimagdalena.lms.TestcontainersConfiguration;
import edu.unimagdalena.lms.entites.Instructor;
import edu.unimagdalena.lms.entites.InstructorProfile;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import(TestcontainersConfiguration.class)
public class InstructorProfileRepositoryTest {

    @Autowired
    InstructorProfileRepository instructorProfileRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Test
    @DisplayName("InstructorProfile: Encuentra perfil por instructor")
    void shouldFindByInstructor() {
        Instructor instructor = instructorRepository.save(
                Instructor.builder().fullName("Diana Vargas").email("dvargas@unimagdalena.edu.co").build());

        instructorProfileRepository.save(InstructorProfile.builder()
                .instructor(instructor).phone("3001234567").bio("Experta en Java").build());

        assertThat(instructorProfileRepository.findByInstructor(instructor)).isPresent();
        assertThat(instructorProfileRepository.findByInstructor(instructor).get().getPhone()).isEqualTo("3001234567");
    }

    @Test
    @DisplayName("InstructorProfile: Encuentra perfil por teléfono")
    void shouldFindByPhone() {
        Instructor instructor = instructorRepository.save(
                Instructor.builder().fullName("Héctor Medina").email("hmedina@unimagdalena.edu.co").build());

        instructorProfileRepository.save(InstructorProfile.builder()
                .instructor(instructor).phone("3109876543").bio("Docente de Redes").build());

        assertThat(instructorProfileRepository.findByPhone("3109876543")).isPresent();
        assertThat(instructorProfileRepository.findByPhone("3109876543").get().getBio()).isEqualTo("Docente de Redes");
    }

    @Test
    @DisplayName("InstructorProfile: Encuentra perfiles por keyword en bio (case insensitive)")
    void shouldFindByBioContainingIgnoreCase() {
        Instructor i1 = instructorRepository.save(
                Instructor.builder().fullName("Natalia Cruz").email("ncruz@unimagdalena.edu.co").build());
        Instructor i2 = instructorRepository.save(
                Instructor.builder().fullName("Camilo Torres").email("ctorres@unimagdalena.edu.co").build());
        Instructor i3 = instructorRepository.save(
                Instructor.builder().fullName("Elena Ríos").email("erios@unimagdalena.edu.co").build());

        instructorProfileRepository.save(InstructorProfile.builder().instructor(i1).phone("111").bio("Especialista en Machine Learning").build());
        instructorProfileRepository.save(InstructorProfile.builder().instructor(i2).phone("222").bio("Docente de machine learning avanzado").build());
        instructorProfileRepository.save(InstructorProfile.builder().instructor(i3).phone("333").bio("Profesora de Bases de Datos").build());

        List<InstructorProfile> result = instructorProfileRepository.findByBioContainingIgnoreCase("machine learning");

        assertThat(result).hasSize(2);
        assertThat(result).extracting(p -> p.getInstructor().getFullName()).contains("Natalia Cruz", "Camilo Torres");
    }
}
