package edu.unimagdalena.lms.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "Courses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Column(nullable = false)
    private String title;

    private String status;

    private Boolean active;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "uploaded_at")
    private Instant uploadedAt;

    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments;

    @OneToMany(mappedBy = "course")
    private Set<Assessment> assessments;

    @OneToMany(mappedBy = "course")
    private Set<Lesson> lessons;
}
