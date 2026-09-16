package com.placement.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(
        uniqueConstraints={
                @UniqueConstraint(columnNames={"student_id","job_id"})
        }
)
public class Application {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_id",nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name="job_id",nullable=false)
    private Job job;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private ApplicationStatus status;

    @Column(nullable=false)
    private LocalDateTime appliedAt;
}
