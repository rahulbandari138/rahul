package com.job.service.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.Instant;

@Data
public class JobRequestDto {
    private Long id;
    @NotBlank(message = "title should not blank")
    private String title;
    @NotBlank(message = "description should not be blank, fill with description")
    private String description;
    private String location;
    private String company;
    private String createdBy;
    private String lastUpdatedBy;
    private Instant createdDate;
    private Instant lastUpdatedDate;
}
