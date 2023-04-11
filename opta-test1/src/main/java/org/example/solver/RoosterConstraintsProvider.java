package org.example.solver;

import org.example.domain.Shift;
import org.example.domain.TimeSlot;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

import java.time.Duration;

import static org.optaplanner.core.api.score.stream.ConstraintCollectors.count;

public class RoosterConstraintsProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                // Hard constraints
                employeeDoesHisJob(constraintFactory),
                employeeHasMinOf1ShiftRest(constraintFactory),
//                employeeDoesOneShiftPerDay(constraintFactory),
                employeeDoes4DaysMaxPerWeek(constraintFactory)
        };
    }

//    Constraint roomConflict(ConstraintFactory constraintFactory) {
//        // A room can accommodate at most one lesson at the same time.
//        return constraintFactory
//                // Select each pair of 2 different lessons ...
//                .forEachUniquePair(Lesson.class,
//                        // ... in the same timeslot ...
//                        Joiners.equal(Lesson::getTimeslot),
//                        // ... in the same room ...
//                        Joiners.equal(Lesson::getRoom))
//                // ... and penalize each pair with a hard weight.
//                .penalize(HardSoftScore.ONE_HARD)
//                .asConstraint("Room conflict");
//    }



    Constraint employeeDoesHisJob(ConstraintFactory constraintFactory) {
        // A room can accommodate at most one lesson at the same time.
        return constraintFactory
                .forEach(Shift.class)
                .filter(shift -> (!shift.getEmployee().getSpeciality().getJobTitle().equals(shift.getJob().getJobTitle())))
//                .join(Shift.class,
//                        Joiners.equal(Shift::getEmployee))
                .penalize(HardSoftScore.ofHard(5))
                .asConstraint("Employee Job Conflict");
    }


    Constraint employeeHasMinOf1ShiftRest(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachUniquePair(Shift.class,
                        Joiners.equal(Shift::getEmployee))
                .filter(((shift, shift2) -> {
                    Duration between = Duration.between(shift.getTimeSlot().getEndTime(), shift2.getTimeSlot().getStartTime());

                    return between.isZero();
                }))
                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Employee Shift Day Conflict");
    }

    Constraint employeeDoesOneShiftPerDay(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEachUniquePair(Shift.class,
                        Joiners.equal(Shift::getEmployee),
                        Joiners.equal(shift -> shift.getTimeSlot().getDayOfWeek()))

                .penalize(HardSoftScore.ONE_HARD)
                .asConstraint("Employee Shift Day Conflict");
    }


//    Constraint employeeDoes4DaysMaxPerWeek(ConstraintFactory constraintFactory) {
//        return constraintFactory
//                .forEach(Shift.class)
//                .join(Shift.class,
//                        Joiners.equal(shift -> shift.getTimeSlot().getWeek(), shift -> shift.getTimeSlot().getWeek()),
//                        Joiners.equal(shift -> shift.getEmployee(), shift -> shift.getEmployee()))
//                .map(((shift, shift2) -> shift2))
//                .groupBy(Shift::getEmployee, count())
//                .filter((employee, count)-> count > 4)
//                .penalize(HardSoftScore.ofHard(1))
//                .asConstraint("Employee Max Week Job Conflict");
//    }


    Constraint employeeDoes4DaysMaxPerWeek(ConstraintFactory constraintFactory) {
        return constraintFactory
                .forEach(Shift.class)
                .join(Shift.class,
                        Joiners.equal(Shift::getEmployee),
                        Joiners.equal(shift -> shift.getTimeSlot().getWeek()))
                .map(((shift, shift2) -> shift))
                .groupBy(Shift::getEmployee, count())
                .filter((employee, count)-> count > 4)
                .penalize(HardSoftScore.ofHard(1))
                .asConstraint("Employee Max Week Job Conflict");
    }
}
