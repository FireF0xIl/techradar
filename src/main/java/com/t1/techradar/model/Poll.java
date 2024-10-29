package com.t1.techradar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Entity
@Data
@Builder
@NoArgsConstructor
@Table(name = "polls")
public class Poll {

    @Id
    @Column(name = "poll_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pollId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tech_id", nullable = false)
    private Technology technology;

    @ManyToOne
    @JoinColumn(name = "ring_id", nullable = false)
    private Ring ring;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

}
