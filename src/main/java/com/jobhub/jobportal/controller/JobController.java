package com.jobhub.jobportal.controller;

import com.jobhub.jobportal.model.Job;
import com.jobhub.jobportal.repository.JobRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

@RestController
@CrossOrigin
@RequestMapping("/api/jobs")
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // Get all jobs
    @GetMapping
    public List<Job> getAllJobs() {

        // Get jobs from MySQL
        List<Job> databaseJobs = jobRepository.findAll();

        // Store jobs using Java ArrayList
        ArrayList<Job> jobs = new ArrayList<>();

        for (Job job : databaseJobs) {
            jobs.add(job);
        }

        return jobs;
    }

    // Add a new job
    @PostMapping
    public Job addJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }

    // Get job by ID
    @GetMapping("/{id}")
    public Job getJobById(@PathVariable int id) {
        return jobRepository.findById(id).orElse(null);
    }
    @GetMapping("/search")
public List<Job> searchJobs(@RequestParam String keyword) {

    List<Job> databaseJobs = jobRepository.findAll();

    ArrayList<Job> result = new ArrayList<>();

    for (Job job : databaseJobs) {

        if (job.getTitle().toLowerCase().contains(keyword.toLowerCase())
                || job.getCompany().toLowerCase().contains(keyword.toLowerCase())
                || job.getLocation().toLowerCase().contains(keyword.toLowerCase())) {

            result.add(job);
        }
    }

    return result;
}
@GetMapping("/sort-by-salary")
public List<Job> sortJobsBySalary() {

    List<Job> jobs = jobRepository.findAll();

    ArrayList<Job> sortedJobs = new ArrayList<>(jobs);

    sortedJobs.sort(
        Comparator.comparingDouble(Job::getSalary).reversed()
    );

    return sortedJobs;
}
}