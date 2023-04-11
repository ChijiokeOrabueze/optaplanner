package org.example.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.util.List;

@PlanningSolution
public class Rooster {



    @ProblemFactCollectionProperty
    @ValueRangeProvider
    private List<Employee> employees;

    @PlanningEntityCollectionProperty
    private List<Shift> shifts;

    @PlanningScore
    private HardSoftScore score;

    // No-arg constructor required for OptaPlanner
    public Rooster() {
    }

    public Rooster(List<Shift> shifts, List<Employee> employees) {
        this.employees = employees;
        this.shifts = shifts;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public HardSoftScore getScore() {
        return score;
    }
}
