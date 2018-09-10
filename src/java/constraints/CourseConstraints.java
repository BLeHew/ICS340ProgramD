package constraints;

import java.util.*;
import java.util.Map.Entry;

import course.*;
import domain.CourseRequisites;

public class CourseConstraints extends HashMap<String, CourseRequisites>{
    
    //private HashMap<String, HashMap<String,String>> constraintTree = new HashMap<String,HashMap<String,String>>();

    public void addConstraint(String lhs,String type, String rhs) {
        if(containsKey(lhs)) {
            get(lhs).add(new Constraint(lhs,type,rhs));
        }else
            put(lhs, new CourseRequisites(new Constraint(lhs,type, rhs)));
               
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Entry<String, CourseRequisites> e : entrySet()) {
            sb.append(e.getKey() + " " + e.getValue() + "\n");
        }
        
        return sb.toString();
    }
    

}
