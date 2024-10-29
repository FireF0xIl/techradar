package com.t1.techradar.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mapping")
public class Mapping {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;

    @ManyToOne
    @JoinColumn(name = "fun_id")
    private Function funId;
}
