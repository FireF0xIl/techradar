package com.t1.techradar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "technologies")
public class Technology {

    @Id
    @Column(name = "tech_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technologyId;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "sec_id")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "ring_id")
    private Ring ring;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "stat_id")
    private Status status;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;
}
