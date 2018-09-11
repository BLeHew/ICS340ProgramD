package constraints;

import java.util.*;

import domain.*;

public class CourseConstraints extends HashMap<String, CourseRequisites>{
    
    
    HashMap<String,HashMap<String,String>> constraintsMap = new HashMap<String,HashMap<String,String>>();

    public void addConstraint(String lhs,String type, String rhs) {
        if(!constraintsMap.containsKey(lhs)) {
            constraintsMap.put(lhs,new HashMap<String,String>());
            constraintsMap.get(lhs).put(rhs, type);
        }else {
            constraintsMap.get(lhs).put(rhs, type);
        }
    }
    
    public void constructConstraintTrees() {
        
        for(String lhs : constraintsMap.keySet()) {
            if(mapContains(lhs) == null) {
                put(lhs,new CourseRequisites(lhs));
            }else {
                put(mapContains(lhs), new CourseRequisites(mapContains(lhs), lhs));
            }
            
        }
        
        
        for(Entry<String,HashMap<String,String>> e : constraintsMap.entrySet()) {
                       
            for(Entry<String,String> cs : e.getValue().entrySet()) {

               String findMap = mapContains(cs.getKey());
               
               
                     
            }
            
        }
        
    }
    public String constContains(String item) {
        for(Entry<String,CourseRequisites> e : entrySet()) {
            if(e.getKey().equals(item)) {
                return e.getKey();
            }
        }
        return null;
    }
    public String mapContains(String item) {
        for(Entry<String, HashMap<String,String>> e : constraintsMap.entrySet()) {
            if(e.getValue().containsKey(item)) {
                return e.getKey();
            }
        }
        return null;
    }

    public void print() {
        constructConstraintTrees();
        
        
        for(Entry<String,CourseRequisites> e : this.entrySet()) {
            e.getValue().print();
        }
        
        }
    }

