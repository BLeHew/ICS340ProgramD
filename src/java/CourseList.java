import java.util.*;

public class CourseList extends HashMap<String,Course> {

    public CourseList() {};

    public void put(Course c) {
        put(c.getName(),c);
    }

    public void addConstraint(String lhs,String type, String rhs) {
        switch(type) {
            case "<":  get(rhs).addPrereq(lhs);
                break;
            case "<=": get(rhs).addCoreq(lhs);
                break;
            default:
                break;
        }
    }
    public int getDayConflicts() {
        int numConflicts = 0;
        for(Course c : values()) {
            if(c.getDayTaken() == '-') {
                numConflicts++;
                c.addConflict();
            }
        }
        return numConflicts;
    }
    public int getConstraintConflicts() {
        int numConflicts = 0;
        for(Course c : values()) {
            for(Course c1 : values()) {
                if(c.hasConflictWith(c1)) {
                    c.addConflict();
                    numConflicts++;
                }
            }
        }
        return numConflicts;
    }
    public int getSemesterDaysConflicts() {
        int numConflicts = 0;
        for(Course c: values()) {
            for(Course c1 : values()) {
                if(c.hasSameDayAndSemesterAs(c1)) {
                    c.addConflict();
                    numConflicts++;
                }
            }
        }
        return numConflicts;
    }
    public void resetCourseConflicts() {
        for(Course c : values()) {
            c.setConflicts(0);
        }
    }
    public void assignDays(Random r) {
        for(Course c : values()) {
            c.setRandomDayFromSchedule(r);
        }
    }
}
