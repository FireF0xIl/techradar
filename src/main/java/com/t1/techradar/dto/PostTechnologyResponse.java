package com.t1.techradar.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PostTechnologyResponse {
    @NotBlank
    public Long id;
    @NotBlank
    public String name;


}