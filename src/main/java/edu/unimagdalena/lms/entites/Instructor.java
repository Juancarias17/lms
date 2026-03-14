package edu.unimagdalena.lms.entites;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "Instructors")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "created_at")
    private Instant created_at;

    @Column(name = "uploaded_at")
    private Instant uploaded_at;

    @OneToMany(mappedBy = "instructor")
    private Set<Course> courses;

    @OneToOne(mappedBy = "instructor")
    private InstructorProfile instructorProfile;

}
