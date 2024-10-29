package com.t1.techradar.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TechnologyDTO {
    @NotBlank
    @NonNull
    public String name;
    @NotBlank
    @NonNull
    public String category;
    @NotBlank
    @NonNull
    public String ring;
    @NotBlank
    @NonNull
    public String section;

    public String description;

}
