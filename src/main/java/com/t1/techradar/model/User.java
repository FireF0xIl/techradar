package com.t1.techradar.model;

import com.t1.techradar.converter.RoleConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@Data
@Builder
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "login", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;


    @ManyToOne
    @Convert(converter = RoleConverter.class)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;
}
