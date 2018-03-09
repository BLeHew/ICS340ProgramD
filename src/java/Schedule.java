import java.util.*;

public class Schedule {
    private int numConflicts;

    private CourseList courses;

    public Schedule(CourseList courses) {
        this.courses = courses;
        assignSemesters();
        assignDays();
    };

    private void assignDays() {

        String week = "SMTWTFA"; //days of the week 'A' is for saturday

        Random r = new Random();

    }

    public int getNumConflicts() {
        return numConflicts;
    }

    public void assignSemesters() {
        Random r = new Random();
        for(Course c : courses.values()) {
            c.setSemTaken(r.nextInt(11));
        }

    }
    public void print() {
        for(Course c : courses.values()) {
            System.out.println(c.getDayTaken());
        }
    }



}
