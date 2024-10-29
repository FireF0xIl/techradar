package com.t1.techradar.service;

import com.t1.techradar.dto.UpdateTechnologyRequest;
import com.t1.techradar.model.Category;
import com.t1.techradar.model.Ring;
import com.t1.techradar.model.Section;
import com.t1.techradar.model.Technology;
import com.t1.techradar.repository.StatusRepository;
import com.t1.techradar.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.t1.techradar.utils.Utils.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TechnologyService {
    private final TechnologyRepository technologyRepository;
    private final StatusRepository statusRepository;

    public List<Technology> findAll() {
        return technologyRepository.findAll();
    }

    public List<Technology> findByFilter(Category category, Section section) {
        if (category == null && section == null) {
            return technologyRepository.findAll();
        } else if (category == null) {
            return technologyRepository.findBySection(section);
        } else if (section == null) {
            return technologyRepository.findByCategory(category);
        } else {
            return technologyRepository.findByCategoryAndSection(category, section);
        }
    }

    public Optional<Technology> findById(Long id) {
        return technologyRepository.findById(id);
    }

    public Technology addTechnology(Technology technology) {
        return technologyRepository.save(technology);
    }

    public boolean deleteTechnology(Long id) {
        return technologyRepository.findById(id).map(t -> {
            technologyRepository.delete(t);
            return true;
        }).orElse(false);
    }

    public boolean archive(Long id) {
        return technologyRepository.findById(id).map(technology -> {
            technology.setStatus(statusRepository.findByStatusName("ARCHIVED").get());
            technologyRepository.save(technology);
            return true;
        }).orElse(false);
    }

    public void update(Long id, UpdateTechnologyRequest request, Ring ring) {
        Technology technology = technologyRepository.findById(id).orElseThrow(() -> NOT_FOUND);
        if (ring != null) {
            technology.setRing(ring);
        }
        if (request.name != null) {
            technology.setName(request.name);
        }
        if (request.description != null) {
            technology.setDescription(request.description);
        }
        technologyRepository.save(technology);

    }
}
