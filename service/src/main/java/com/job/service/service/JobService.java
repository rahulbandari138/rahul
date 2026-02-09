package com.job.service.service;

import com.job.service.entity.JobEntity;
import com.job.service.repo.JobRepo;
import com.job.service.request.JobRequestDto;
import com.job.service.response.JobResponseDto;
import com.job.service.response.JobResponseDto2;
import com.job.service.security.UserContext;
import com.job.service.security.UserContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepo jobRepo;

    public JobResponseDto createJob(JobRequestDto dto){

        UserContext userContext = UserContextHolder.get();
        if(!userContext.hasRole("ADMIN") && !userContext.hasRole("EMPLOYER")){
            throw new AccessDeniedException("Invalid role");
        } JobEntity entity = new JobEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
        entity.setCompany(dto.getCompany());
        entity.setCreatedBy(userContext.getUsername());
        entity.setLastUpdatedBy(userContext.getUsername());
        entity.setCreatedDate(Instant.now());
        entity.setLastUpdatedDate(Instant.now());
        jobRepo.save(entity);
        return JobResponseDto.builder().msg("Job Created Successfully").build();
    }

    public void deleteJob(Long id) {

        UserContext userContext = UserContextHolder.get();
        if(!userContext.hasRole("ADMIN") && !userContext.hasRole("EMPLOYER")){
            throw new AccessDeniedException("Invalid role");
        }
        if (!jobRepo.existsById(id)) {
            throw new RuntimeException("Job not found with id: " + id);
        }

        jobRepo.deleteById(id);
    }


    public List<JobResponseDto2> getAllJobs() {
        return jobRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


    private JobResponseDto2 mapToDto(JobEntity job) {
        return new JobResponseDto2(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getLocation(),
                job.getCompany()
        );
    }


}
