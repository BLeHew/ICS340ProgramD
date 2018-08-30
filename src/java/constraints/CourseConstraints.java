package constraints;

import java.util.*;

import course.*;

public class CourseConstraints extends HashMap<String,CourseRequisites> {

    public void addConstraint(String lhs,String type, String rhs) {
        if(!containsKey(lhs)) {
            put(lhs, new CourseRequisites());
        }
        switch(type) {
            case "<":  get(lhs).addNextCourse(rhs);
                break;
            case "<=": get(lhs).addSameAndNextCourse(rhs);
                break;
            default:
                break;
        }
    }
}
