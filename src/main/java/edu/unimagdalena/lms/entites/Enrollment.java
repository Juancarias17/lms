package edu.unimagdalena.lms.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "Enrollments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    private String status;

    @Column(name = "enrolled_at")
    private Instant enrolledAt;
}
