package constraints;

import java.util.*;

public class CourseRequisites {
    private HashSet<String> nextCourses = new HashSet<>();
    private HashSet<String> sameAndNextCourses = new HashSet<>();

    public void addNextCourse(String s) {
        nextCourses.add(s);
    }
    public void addSameAndNextCourse(String s) {
        sameAndNextCourses.add(s);
    }
}
