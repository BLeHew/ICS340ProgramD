package schedule;

import java.util.*;

import constraints.CourseConstraints;
import schedule.*;

public class CourseSchedules extends HashMap<String,CourseSchedule> {
    private static CourseSchedules singleton = new CourseSchedules();
    
    private CourseSchedules() {
        
    }
    public static CourseSchedules getInstance() {
        return singleton;
    }
}
