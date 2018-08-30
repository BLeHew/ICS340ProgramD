package course;
import java.util.*;

import constraints.*;
import domain.*;
import schedule.*;


public class Courses extends HashMap<String,Course> {

    private CourseSchedules courseSchedules = new CourseSchedules();
    private CourseDomains courseDomains = new CourseDomains();
    private CourseConstraints courseConstraints = new CourseConstraints();

    private Semester[] sems = new Semester[11];

    public Courses() {
        for(int i = 0; i < 11; i++) {
            sems[i] = new Semester(i);
        }
    }

    public void put(Course c, String fallDays, String springDays, String summerDays) {
        put(c.getName(),c);
        courseSchedules.put(c.getName(), new CourseSchedule(fallDays, springDays, summerDays));
        courseDomains.put(c.getName(), new CourseDomain(fallDays,springDays,summerDays));
    }
    public CourseSchedule getCourseSchedule(String course) {
        return courseSchedules.get(course);
    }
    public void put(String lhs, String type, String rhs) {
        courseConstraints.addConstraint(lhs, type, rhs);
    }
    public void setSemTaken(String course, int sem) {
        sems[sem].add(course,this);
        get(course).setSemTaken(sem);
        courseDomains.get(course);
    }

}
