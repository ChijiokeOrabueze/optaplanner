package org.example.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimeSlot {

    private Integer week;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeSlot(Integer week, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.week = week;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimeSlot(Integer week, DayOfWeek dayOfWeek, LocalTime startTime) {
        this(week, dayOfWeek, startTime, startTime.plusMinutes(50));
    }

    @Override
    public String toString() {
        return dayOfWeek + " " + startTime;
    }

    // ************************************************************************
    // Getters and setters
    // ************************************************************************

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Integer getWeek() {
        return week;
    }
}

