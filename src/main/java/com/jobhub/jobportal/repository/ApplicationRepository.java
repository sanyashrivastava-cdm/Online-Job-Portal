package com.jobhub.jobportal.repository;

import com.jobhub.jobportal.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository
        extends JpaRepository<Application, Integer> {

    List<Application> findByUserId(int userId);

    List<Application> findByJobId(int jobId);
}