package com.t1.techradar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class DashBoardResponse {
    @NotBlank
    @Size(max = 50)
    private String category;

    @NotBlank
    @Size(max = 50)
    private String section;

    @NotBlank
    @Size(max = 50)
    private String ring;

    @NotNull
    private Map<String, Integer> votes;
}
