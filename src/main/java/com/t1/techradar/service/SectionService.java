package com.t1.techradar.service;

import com.t1.techradar.model.Section;
import com.t1.techradar.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SectionService {
    private final SectionRepository sectionRepository;

    public Optional<Section> findByName(String name) {
        return sectionRepository.findBySecName(name);
    }

}