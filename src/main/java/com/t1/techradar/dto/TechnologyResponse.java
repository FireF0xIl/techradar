package com.t1.techradar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TechnologyResponse {

    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String description;

    @NotBlank
    @Size(max = 50)
    private String category;

    @NotBlank
    @Size(max = 50)
    private String section;

    @NotBlank
    @Size(max = 50)
    private String ring;




}
