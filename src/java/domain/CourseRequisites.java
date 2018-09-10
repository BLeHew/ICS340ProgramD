package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import constraints.Constraint;

public class CourseRequisites extends HashMap<Constraint, CourseRequisites> {
    
    public CourseRequisites(Constraint constraint) {        
        put(constraint, new CourseRequisites());
    }
    public CourseRequisites() {
        
    }
    public void add(Constraint constraint) {
        for(Entry<Constraint, CourseRequisites> e : entrySet()) {
            if(e.getKey().getLhs().equals(constraint.getRhs())) {
                e.getValue().put(constraint, new CourseRequisites());
            }
        }
        
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Entry<Constraint, CourseRequisites> e : entrySet()) {
            sb.append(e.getKey().getRhs() + " ");
        }
        return sb.toString();
    }
    
}
