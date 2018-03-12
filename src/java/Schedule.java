import java.util.*;

public class Schedule {
    private int numConflicts;

    private Semester[] sems = new Semester[11];

    private CourseList courses;

    private static final long SEED = 1111112;
    public Schedule(CourseList courses) {
        for(int i = 0; i < sems.length ; i++) {
            sems[i] = new Semester(i);
        }
        this.courses = courses;
        assignSemesters();
        assignDays();
        addUpConflicts();


        print();
    };

    private void checkIfDayIsValid() {
        for(Course c : courses.values()) {
            if(c.getDayTaken() == '-') {
                numConflicts++;
                c.addConflict();
            }
        }
    }

    private void addUpConflicts() {
        checkIfDayIsValid();
        checkIfSemestersAreValid();
    }

    private void checkIfSemestersAreValid() {
        for(int i = 0; i < sems.length; i++) {
            if(!sems[i].isNumDaysValid()) {
                for(Course c: courses.values()) {
                    if(c.getSemTaken() == i) {
                        c.addConflict();
                    }
                }
            }
        }
    }

    private void assignDays() {

        Random r = new Random();
        r.setSeed(SEED);
        for(Course c : courses.values()) {
            c.setRandomDayFromSchedule(r.nextInt(c.getSchedule().length()));
        }
    }

    public int getNumConflicts() {
        return numConflicts;
    }

    public void assignSemesters() {
        Random r = new Random();
        r.setSeed(SEED);
        int semester;
        for(Course c : courses.values()) {
            semester = r.nextInt(11);
            c.setSemTaken(semester);
            sems[semester].add(c);
        }

    }
    public void print() {
        String[] output = new String[11];
        for(int i = 0; i < output.length; i++) {
            output[i] = new String("");
        }
        for(Course c : courses.values()) {
             //System.out.println(c.getName() + " " + c.getSemTaken() + " " + c.getDayTaken());
             output[c.getSemTaken()] += c.getNumConflicts() + "-" + c.getName() + "\t" + c.getDayTaken() + "\t";
        }
        for(int i = 0; i < output.length; i++) {
            System.out.println(i + ". " + output[i]);
        }
    }



}
