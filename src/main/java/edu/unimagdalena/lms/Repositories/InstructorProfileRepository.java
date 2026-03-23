package edu.unimagdalena.lms.Repositories;

import edu.unimagdalena.lms.entites.Instructor;
import edu.unimagdalena.lms.entites.InstructorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InstructorProfileRepository extends JpaRepository<InstructorProfile, Long> {

    Optional<InstructorProfile> findByInstructor(Instructor instructor);
    Optional<InstructorProfile> findByPhone(String phone);
    List<InstructorProfile> findByBioContainingIgnoreCase(String keyword);
}
