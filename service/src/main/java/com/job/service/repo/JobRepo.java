package com.job.service.repo;

import com.job.service.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepo extends JpaRepository<JobEntity, Long> {
}
