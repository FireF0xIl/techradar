package com.t1.techradar.service;

import com.t1.techradar.model.Status;
import com.t1.techradar.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final StatusRepository statusRepository;

    public Optional<Status> findByName(String name) {
        return statusRepository.findByStatusName(name);
    }

}