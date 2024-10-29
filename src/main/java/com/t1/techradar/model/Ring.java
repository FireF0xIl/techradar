package com.t1.techradar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "rings")
public class Ring {

    @Id
    @Column(name = "ring_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ringId;

    @Column(name = "ring_name", nullable = false)
    private String ringName;
}