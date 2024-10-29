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
@Table(name = "sections")
public class Section {

    @Id
    @Column(name = "sec_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long secId;

    @Column(name = "sec_name", nullable = false)
    private String secName;
}
