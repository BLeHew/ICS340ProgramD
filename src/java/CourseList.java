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
}
