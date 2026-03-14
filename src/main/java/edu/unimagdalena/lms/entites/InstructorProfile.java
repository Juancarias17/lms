package edu.unimagdalena.lms.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "InstructorProfiles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class InstructorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;

    private String phone;

    private String bio;
}
