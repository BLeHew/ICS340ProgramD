package course;
import java.util.*;

import conflict.*;
import constraints.*;
import domain.*;
import schedule.*;


public class Courses extends HashMap<String,Course> {

    public static CourseSchedules courseSchedules = new CourseSchedules();
    public static CourseDomains courseDomains = new CourseDomains(30);
    public static CourseConstraints courseConstraints = new CourseConstraints();

    private int numConflicts = 0;

    private Semester[] sems = new Semester[11];

    public static final long SEED = 200000;
    public static final Random rand = new Random();

    public Courses() {
        for(int i = 0; i < 11; i++) {
            sems[i] = new Semester(i);
        }
    }
    public void put(Course c, String fallDays, String springDays, String summerDays) {
        put(c.getName(),c);
        courseSchedules.put(c.getName(), new CourseSchedule(fallDays, springDays, summerDays));
        courseDomains.add(c.getName(), new CourseDomain());
    }
    public String getCourseSchedule(String course) {
        return courseSchedules.get(course).getDays(get(course).getSemTaken());
    }
    public Character getDayFromSchedule(String course, int index) {
        return courseSchedules.get(course).getDayFromSchedule(get(course).getSemTaken(), index);
    }
    public Character getRandomDayFromSchedule(String course) {
        return courseSchedules.get(course).getRandomDayFromSchedule(get(course).getSemTaken());
    }
    public void addConflict(String course, Conflict c) {
        courseDomains.get(course).addConflict(c);
    }
    public void removeMultipleConflicts(String courseName, Conflict conflict) {
        courseDomains.get(courseName).removeMultipleConflicts(conflict);
    }
    public void addMultipleConflicts(String courseName, Conflict conflict) {
        courseDomains.get(courseName).addMultipleConflicts(conflict);
    }
    public CourseDomain getCourseDomain(String course) {
        return courseDomains.get(course);
    }
    public void put(String lhs, String type, String rhs) {
        courseConstraints.addConstraint(lhs, type, rhs);
    }
    public int getNumConflicts() {
        return numConflicts;
    }
    public void setSemTaken(String course, int sem) {

        if(get(course).getSemTaken() != null) {
            sems[get(course).getSemTaken()].remove(course,this);
        }

        get(course).setSemTaken(sem);

        sems[sem].add(course,this);

    }

    public void checkConflicts() {
        System.out.println(courseConstraints);
    }



}
