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
    public boolean hasConstraints(String course) {
        return constraintsMap.containsKey(course);
    }
    public boolean coursesHaveConflict(String lhs, int lhsSem, String rhs, int rhsSem) {
        if(constraintsMap.containsKey(lhs)) {
            if(constraintsMap.get(lhs).get(rhs).equals("<")) {
                if(lhsSem >= rhsSem) {
                    return true;
                }
            }else {
                if(lhsSem > rhsSem) {
                    return true;
                }
            }
        }
        return false;
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


