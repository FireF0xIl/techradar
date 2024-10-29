package com.t1.techradar.service;

import com.t1.techradar.model.Category;
import com.t1.techradar.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByCatName(name);
    }
}
