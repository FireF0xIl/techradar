package com.t1.techradar.controller;

import com.t1.techradar.dto.PostTechnologyResponse;
import com.t1.techradar.dto.TechnologyDTO;
import com.t1.techradar.dto.TechnologyResponse;
import com.t1.techradar.dto.UpdateTechnologyRequest;
import com.t1.techradar.model.Category;
import com.t1.techradar.model.Ring;
import com.t1.techradar.model.Section;
import com.t1.techradar.model.Technology;
import com.t1.techradar.repository.StatusRepository;
import com.t1.techradar.service.CategoryService;
import com.t1.techradar.service.RingService;
import com.t1.techradar.service.SectionService;
import com.t1.techradar.service.TechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.t1.techradar.utils.Utils.BAD_REQUEST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TechnologyController {
    private final SectionService sectionService;
    private final RingService ringService;
    private final CategoryService categoryService;
    private final TechnologyService technologyService;
    private final StatusRepository statusRepository;


    @GetMapping("/technology")
    public ResponseEntity<List<TechnologyResponse>> findAllByFilters(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String section
    ) {

        Section sectionFilter = null;
        Category categoryFilter = null;
        if (section != null) {
            sectionFilter = sectionService.findByName(section).orElseThrow(() -> BAD_REQUEST);
        }
        if (category != null) {
            categoryFilter = categoryService.findByName(category).orElseThrow(() -> BAD_REQUEST);
        }

        List<TechnologyResponse> resp = technologyService.findByFilter(categoryFilter, sectionFilter).stream().map(technology -> new TechnologyResponse(
                technology.getTechnologyId(),
                technology.getName(),
                technology.getDescription(),
                technology.getCategory().getCatName(),
                technology.getSection().getSecName(),
                technology.getRing().getRingName()
        )).toList();

        return ResponseEntity.ok(resp);
    }


    @PostMapping("/technology")
    public ResponseEntity<PostTechnologyResponse> addTechnology(@RequestBody TechnologyDTO technologyDTO) {
        Section section = sectionService.findByName(technologyDTO.section).orElseThrow(() -> BAD_REQUEST);
        Ring ring = ringService.findByName(technologyDTO.ring).orElseThrow(() -> BAD_REQUEST);
        Category category = categoryService.findByName(technologyDTO.category).orElseThrow(() -> BAD_REQUEST);
        Technology technology =
                technologyService.addTechnology(
                Technology
                        .builder()
                        .name(technologyDTO.name)
                        .section(section)
                        .ring(ring)
                        .category(category)
                        .description(technologyDTO.description)
                        .status(statusRepository.findByStatusName("NEW").get())
                        .updateTime(LocalDateTime.now())
                        .build());
        return ResponseEntity.ok(new PostTechnologyResponse(technology.getTechnologyId(),technology.getName()));
    }
    @DeleteMapping("/technology/{id}")
    public ResponseEntity<String> deleteTechnology(@PathVariable(name = "id") Long id) {

        if (technologyService.deleteTechnology(id)) {
            return ResponseEntity.ok("Успешная операция, технология удалена");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Технология с указанным ID не найдена");
        }
    }


    @PatchMapping("/technology/archive/{id}")
    public ResponseEntity<String> archiveTechnology(@PathVariable(name = "id") Long id) {
        if (technologyService.archive(id)) {
            return ResponseEntity.ok("Успешная операция, технология архивирована");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Технология с указанным ID не найдена");
        }
    }

    @PatchMapping("/technology/{id}")
    public ResponseEntity<String> updateTechnology(@PathVariable(name = "id") Long id,
                                                   @RequestBody UpdateTechnologyRequest request
                                                   ) {

        Ring ring;
        if (request.ring != null) {
            ring = ringService.findByName(request.ring).orElseThrow(() -> BAD_REQUEST);
        } else {
            ring = null;
        }
        technologyService.update(id, request, ring);
        return ResponseEntity.ok("Успешная операция, данные технологии обновлены");
    }

}
