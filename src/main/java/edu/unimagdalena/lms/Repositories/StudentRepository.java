package edu.unimagdalena.lms.Repositories;

import edu.unimagdalena.lms.entites.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository  extends JpaRepository<Student, Long> {

    Optional<Student> findByFullName(String fullName);
    List<Student> findByFullNameIn(List<String> names);
    Optional<Student> findByFullNameStartingWithIgnoreCase(String word);
}
