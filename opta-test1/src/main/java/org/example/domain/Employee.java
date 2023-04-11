package org.example.domain;


public class Employee {

    private String name;
    private Job speciality;


    @Override
    public String toString() {
        return name + " " + speciality.getJobTitle();
    }

    public Employee (String name, Job speciality) {
        this.speciality = speciality;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Job getSpeciality() {
        return speciality;
    }
}
