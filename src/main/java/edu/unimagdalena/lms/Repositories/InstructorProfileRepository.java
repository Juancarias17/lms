package edu.unimagdalena.lms.Repositories;

import edu.unimagdalena.lms.entites.InstructorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorProfileRepository extends JpaRepository<InstructorProfile, Long> {
}
