package course;
import java.util.*;

import constraints.*;
import domain.*;
import schedule.*;


public class Courses extends HashMap<String,Course> {

    private CourseSchedules courseSchedules = new CourseSchedules();
    private CourseDomains courseDomains = new CourseDomains();
    private CourseConstraints courseConstraints = new CourseConstraints();
    private ArrayList<String> badCourses = new ArrayList<>();

    private Semester[] sems = new Semester[11];

    public Courses() {
        for(int i = 0; i < 11; i++) {
            sems[i] = new Semester(i);
        }
    }
    public boolean hasBadCourse() {
        return badCourses.size() > 0;
    }
    public void put(Course c, String fallDays, String springDays, String summerDays) {
        put(c.getName(),c);
        courseSchedules.put(c.getName(), new CourseSchedule(fallDays, springDays, summerDays));
        courseDomains.put(c.getName(), new CourseDomain(fallDays,springDays,summerDays));
    }
    public String getCourseSchedule(String course) {
        return courseSchedules.get(course).getDays(get(course).getSemTaken());
    }
    public CourseDomain getCourseDomain(String course) {
        return courseDomains.get(course);
    }
    public void put(String lhs, String type, String rhs) {
        courseConstraints.addConstraint(lhs, type, rhs);
    }
    public void changeCourseSemester(String course,int sem) {
        sems[get(course).getSemTaken()].remove(course, this);

        get(course).setSemTaken(sem);
        sems[sem].add(course, this);

        if(get(course).getDayTaken() == '-') {
            badCourses.add(course);
        }

    }
    public boolean validateDays() {
        if(badCourses.size() > 0) {
            for(String s : badCourses) {
                if(!sems[get(s).getSemTaken()].checkIfValid(s, this)) {
                    return false;
                }
            }
        }
        return true;
    }
    public void setSemTaken(String course, int sem) {
        get(course).setSemTaken(sem);
        sems[sem].add(course,this);
    }

}
