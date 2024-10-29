package com.t1.techradar.service;

import com.t1.techradar.model.Ring;
import com.t1.techradar.repository.RingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RingService {
    private final RingRepository ringRepository;

    public Optional<Ring> findByName(String name) {
        return ringRepository.findByRingName(name);
    }
    public List<Ring> getAll() {
        return ringRepository.findAll();
    }

}