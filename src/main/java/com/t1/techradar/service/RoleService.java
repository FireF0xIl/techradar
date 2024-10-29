package com.t1.techradar.service;

import com.t1.techradar.model.Role;
import com.t1.techradar.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> getRole(Long id) {
        return roleRepository.findById(id);
    }
    public Optional<Role> getRoleByName(String role) {
        return roleRepository.findByRoleName(role);
    }
}
