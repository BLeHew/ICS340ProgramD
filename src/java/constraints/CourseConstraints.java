package constraints;

import java.util.*;
import java.util.Map.Entry;

import conflict.Conflict;

public class CourseConstraints{


    HashMap<String,HashMap<String,String>> constraintsMap = new HashMap<String,HashMap<String,String>>();

    public void addConstraint(String lhs,String type, String rhs) {
        if(!constraintsMap.containsKey(lhs)) {
            constraintsMap.put(lhs, new HashMap<String,String>());
        }
        constraintsMap.get(lhs).put(rhs,type);
    }
    
    public HashMap<String,String> getCourseConflicts(String course){
        return constraintsMap.get(course);
        
    }
    public Collection<String> getCoursesWithConflicts(){
        return constraintsMap.keySet();
    }

    public void print() {
        System.out.println("lhsConstraints: ");
        for(Entry<String, HashMap<String,String>> ents : constraintsMap.entrySet()) {
            
            System.out.print(ents.getKey() + " ");
            
            for(Entry<String,String> entries : ents.getValue().entrySet()) {
                System.out.print(entries.getValue() + " " + entries.getKey() + " ");
            }
            System.out.println();
        }
    }

    }


