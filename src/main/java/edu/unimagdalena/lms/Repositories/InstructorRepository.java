package edu.unimagdalena.lms.Repositories;

import edu.unimagdalena.lms.entites.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
