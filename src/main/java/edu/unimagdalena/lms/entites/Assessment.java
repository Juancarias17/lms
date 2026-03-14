package edu.unimagdalena.lms.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "Assessments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    private String type;

    private String score;

    @Column(name = "taken_at")
    private Instant takenAt;
}
