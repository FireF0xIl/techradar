package com.t1.techradar.repository;

import com.t1.techradar.model.Category;
import com.t1.techradar.model.Section;
import com.t1.techradar.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {

    List<Technology> findByCategory(Category category);
    List<Technology> findBySection(Section section);
    List<Technology> findByCategoryAndSection(Category category, Section section);

}
