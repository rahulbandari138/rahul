package com.job.service.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseDto2 {

    private Long id;
    private String title;
    private String description;
    private String location;
    private String company;
}
