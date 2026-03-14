package edu.unimagdalena.lms.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Lessons")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false)
    private String title;

    @Column(name = "order_index")
    private int orderIndex;
}
