package com.jobhub.jobportal.controller;

import com.jobhub.jobportal.model.Application;
import com.jobhub.jobportal.repository.ApplicationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationRepository applicationRepository;

    public ApplicationController(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    // Apply for a job
    @PostMapping
    public Application applyForJob(@RequestBody Application application) {

        application.setStatus("Applied");

        return applicationRepository.save(application);
    }

    // Get applications of a user
    @GetMapping("/user/{userId}")
    public List<Application> getUserApplications(
            @PathVariable int userId) {

        return applicationRepository.findByUserId(userId);
    }

    // Get applicants for a job
    @GetMapping("/job/{jobId}")
    public List<Application> getJobApplications(
            @PathVariable int jobId) {

        return applicationRepository.findByJobId(jobId);
    }
}