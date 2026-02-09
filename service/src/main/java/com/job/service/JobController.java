package com.job.service;

import com.job.service.request.JobRequestDto;
import com.job.service.response.JobResponseDto;
import com.job.service.response.JobResponseDto2;
import com.job.service.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobController {

    private final JobService service;

    @PostMapping
    public JobResponseDto createJob(@Valid @RequestBody JobRequestDto dto){
        return service.createJob(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        service.deleteJob(id);
        return ResponseEntity.ok("Job deleted successfully");
    }

    @GetMapping("/alljobs")
    public ResponseEntity<List<JobResponseDto2>> getAllJobs() {
        List<JobResponseDto2> jobs = service.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

}
