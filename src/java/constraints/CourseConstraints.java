package constraints;

import java.util.*;

import domain.*;

public class CourseConstraints extends ArrayList<CourseRequisites>{

    //private HashMap<String, HashMap<String,String>> constraintTree = new HashMap<String,HashMap<String,String>>();

    public void addConstraint(String lhs,String type, String rhs) {

        if(!hasInstanceOf(lhs)) {
            add(new CourseRequisites(lhs, type, rhs));
        }else {
            get(getIndex(lhs)).add(lhs,type,rhs);
            /*
            CourseRequisites newCR = get(getIndex(lhs)).add(lhs,type,rhs);
            if(newCR != null) {
                add(newCR);
            }
            */

        }
    }
    public boolean hasInstanceOf(String lhs) {
        for(CourseRequisites  cr : this) {
            if(cr.getRoot().equals(lhs)) {
                return true;
            }
        }
        return false;
    }
    public int getIndex(String lhs) {
        int i = 0;
        for(CourseRequisites cr : this) {
            if(cr.contains(lhs)) {
                return i;
            }
            i++;
        }
        return i;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(CourseRequisites cr : this) {
            sb.append(cr.getRoot() + " " +  cr + "\n");
        }


        return sb.toString();
    }


}
