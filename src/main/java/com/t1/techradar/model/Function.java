package com.t1.techradar.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "functions")
public class Function {

    @Id
    @Column(name = "fun_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fun_name", nullable = false)
    private String functionName;
}
