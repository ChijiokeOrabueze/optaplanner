package org.example.domain;

public class Job {
    private String jobTitle;

    public Job(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return jobTitle;
    }

    // ************************************************************************
    // Getters and setters
    // ************************************************************************

    public String getJobTitle() {
        return jobTitle;
    }
}
