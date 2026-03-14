package edu.unimagdalena.lms.Repositories;

import edu.unimagdalena.lms.entites.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository  extends JpaRepository<Student, Long> {
}
