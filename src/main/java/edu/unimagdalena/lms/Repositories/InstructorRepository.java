package edu.unimagdalena.lms.Repositories;

import edu.unimagdalena.lms.entites.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Optional<Instructor> findByEmail(String email);
    Optional<Instructor> findByFullName(String fullName);
    List<Instructor> findByFullNameContainingIgnoreCase(String keyword);
}
