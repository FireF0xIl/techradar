package com.t1.techradar.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "statuses")
public class Status {

    @Id
    @Column(name = "stat_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;

    @Column(name = "stat_name", nullable = false)
    private String statusName;
}
