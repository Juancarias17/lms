package edu.unimagdalena.lms.Repositories;

import edu.unimagdalena.lms.entites.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
