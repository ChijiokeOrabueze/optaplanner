package org.example;

import org.example.domain.*;
import org.example.solver.RoosterConstraintsProvider;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SolverFactory<Rooster> solverFactory = SolverFactory.create(new SolverConfig()
                .withSolutionClass(Rooster.class)
                .withEntityClasses(Shift.class)
                .withConstraintProviderClass(RoosterConstraintsProvider.class)
                // The solver runs only for 5 seconds on this small dataset.
                // It's recommended to run for at least 5 minutes ("5m") otherwise.
                .withTerminationSpentLimit(Duration.ofSeconds(5)));

        // Load the problem
        Rooster problem = generateDemoData();

        // Solve the problem
        Solver<Rooster> solver = solverFactory.buildSolver();
        Rooster solution = solver.solve(problem);

        // Visualize the solution
        printRooster(solution);
    }

    public static Rooster generateDemoData() {
        HashMap<String, TimeSlot> timeslots = new HashMap<>();
        for (Integer i = 1; i <=3; i++) {


            timeslots.put("mondayMorning" + i, new TimeSlot(i, DayOfWeek.MONDAY, LocalTime.of(7, 00), LocalTime.of(19, 00)));
            timeslots.put("mondayNight"+ i, new TimeSlot(i, DayOfWeek.MONDAY, LocalTime.of(19, 00), LocalTime.of(7, 00)));

            timeslots.put("tuesdayMorning"+ i, new TimeSlot(i, DayOfWeek.TUESDAY, LocalTime.of(7, 00), LocalTime.of(19, 00)));
            timeslots.put("tuesdayNight"+ i, new TimeSlot(i, DayOfWeek.TUESDAY, LocalTime.of(19, 00), LocalTime.of(7, 00)));

            timeslots.put("wednesdayMorning"+ i, new TimeSlot(i, DayOfWeek.WEDNESDAY, LocalTime.of(7, 00), LocalTime.of(19, 00)));
            timeslots.put("wednesdayNight"+ i, new TimeSlot(i, DayOfWeek.WEDNESDAY, LocalTime.of(19, 00), LocalTime.of(7, 00)));

            timeslots.put("thursdayMorning"+ i, new TimeSlot(i, DayOfWeek.THURSDAY, LocalTime.of(7, 00), LocalTime.of(19, 00)));
            timeslots.put("thursdayNight"+ i, new TimeSlot(i, DayOfWeek.THURSDAY, LocalTime.of(19, 00), LocalTime.of(7, 00)));

            timeslots.put("fridayMorning"+ i, new TimeSlot(i, DayOfWeek.FRIDAY, LocalTime.of(7, 00), LocalTime.of(19, 00)));
            timeslots.put("fridayNight"+ i, new TimeSlot(i, DayOfWeek.FRIDAY, LocalTime.of(19, 00), LocalTime.of(7, 00)));

            timeslots.put("saturdayMorning"+ i, new TimeSlot(i, DayOfWeek.SATURDAY, LocalTime.of(7, 00), LocalTime.of(19, 00)));
            timeslots.put("saturdayNight"+ i, new TimeSlot(i, DayOfWeek.SATURDAY, LocalTime.of(19, 00), LocalTime.of(7, 00)));

            timeslots.put("sundayMorning"+ i, new TimeSlot(i, DayOfWeek.SUNDAY, LocalTime.of(7, 00), LocalTime.of(19, 00)));
            timeslots.put("sundayNight"+ i, new TimeSlot(i, DayOfWeek.SUNDAY, LocalTime.of(19, 00), LocalTime.of(7, 00)));
        }
        Job nurse = new Job("nurse");
        Job careGiver = new Job("careGiver");

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(new Employee("homer", nurse));
        employeeList.add(new Employee("marie", nurse));
        employeeList.add(new Employee("ada", nurse));
        employeeList.add(new Employee("john", nurse));
        employeeList.add(new Employee("kemi", nurse));
        employeeList.add(new Employee("ola", nurse));
        employeeList.add(new Employee("yomi", careGiver));
        employeeList.add(new Employee("abdul", careGiver));
        employeeList.add(new Employee("taiwo", careGiver));
        employeeList.add(new Employee("mance", careGiver));


        List<Shift> shifts = new ArrayList<>();
        long id = 0;

        for (Integer i = 1; i <=3; i++) {


            shifts.add(new Shift(id++, timeslots.get("mondayMorning" + i), nurse));
            shifts.add(new Shift(id++, timeslots.get("mondayMorning" + i), careGiver));
            shifts.add(new Shift(id++, timeslots.get("mondayNight" + i), nurse));
            shifts.add(new Shift(id++, timeslots.get("mondayNight" + i), careGiver));

            shifts.add(new Shift(id++, timeslots.get("tuesdayMorning" + i), nurse));
            shifts.add(new Shift(id++, timeslots.get("tuesdayMorning" + i), careGiver));
            shifts.add(new Shift(id++, timeslots.get("tuesdayNight" + i), nurse));
            shifts.add(new Shift(id++, timeslots.get("tuesdayNight" + i), careGiver));

            shifts.add(new Shift(id++, timeslots.get("wednesdayMorning" + i), nurse));
            shifts.add(new Shift(id++, timeslots.get("wednesdayMorning" + i), careGiver));
            shifts.add(new Shift(id++, timeslots.get("wednesdayNight" + i), nurse));
            shifts.add(new Shift(id++, timeslots.get("wednesdayNight" + i), careGiver));

            shifts.add(new Shift(id++, timeslots.get("thursdayMorning" + i), nurse));
            shifts.add(new Shift(id++, timeslots.get("thursdayMorning" + i), careGiver));
            shifts.add(new Shift(id++, timeslots.get("thursdayNight" + i), nurse));
            shifts.add(new Shift(id++, timeslots.get("thursdayNight" + i), careGiver));

            shifts.add(new Shift(id++, timeslots.get("fridayMorning" + i), nurse));
            shifts.add(new Shift(id++, timeslots.get("fridayMorning" + i), careGiver));
            shifts.add(new Shift(id++, timeslots.get("fridayNight" + i), nurse));
            shifts.add(new Shift(id++, timeslots.get("fridayNight" + i), careGiver));

            shifts.add(new Shift(id++, timeslots.get("saturdayMorning" + i), nurse));
            shifts.add(new Shift(id++, timeslots.get("saturdayMorning" + i), careGiver));
            shifts.add(new Shift(id++, timeslots.get("saturdayNight" + i), nurse));
            shifts.add(new Shift(id++, timeslots.get("saturdayNight" + i), careGiver));

            shifts.add(new Shift(id++, timeslots.get("sundayMorning" + i), nurse));
            shifts.add(new Shift(id++, timeslots.get("sundayMorning" + i), careGiver));
            shifts.add(new Shift(id++, timeslots.get("sundayNight" + i), nurse));
            shifts.add(new Shift(id++, timeslots.get("sundayNight" + i), careGiver));
        }

        return new Rooster(shifts, employeeList);
    }


    private static void printRooster (Rooster rooster) {
        for (Shift shift: rooster.getShifts()) {

            System.out.println("-----------------------------------------------------start");
            System.out.println(shift.toString());
            System.out.println("employee: " + shift.getEmployee());
            System.out.println("job: " + shift.getJob());
            System.out.println("-----------------------------------------------------end");

        }

        System.out.println(rooster.getScore());
    }
}