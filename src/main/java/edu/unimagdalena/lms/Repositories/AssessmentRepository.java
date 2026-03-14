package edu.unimagdalena.lms.Repositories;

import edu.unimagdalena.lms.entites.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
}
