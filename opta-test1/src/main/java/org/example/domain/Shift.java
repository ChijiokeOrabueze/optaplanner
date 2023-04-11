package org.example.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class Shift {

    @PlanningId
    private long id;
    private TimeSlot timeSlot;
    private Job job;
    @PlanningVariable
    private Employee employee;

    public Shift() {
    }

    public Shift(long id, TimeSlot timeSlot, Job job) {
        this.id = id;
        this.timeSlot = timeSlot;
        this.job = job;
    }

    public Shift(long id, TimeSlot timeSlot, Job job, Employee employee) {
        this(id, timeSlot, job);
        this.employee = employee;
    }

    @Override
    public String toString() {
        return timeSlot.toString() + "(" + id + ")";
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public Job getJob() {
        return job;
    }

    public Employee getEmployee() {
        return employee;
    }
}
