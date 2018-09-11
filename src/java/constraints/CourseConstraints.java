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
            
            String spot = mapContains(lhs);
            
            if(spot != null) {
                put(spot, new CourseRequisites(spot, lhs));
            }else {
                put(lhs, new CourseRequisites(lhs));
            } 
            
        }
        
        
        for(Entry<String,HashMap<String,String>> e : constraintsMap.entrySet()) {
                       
            for(Entry<String,String> cs : e.getValue().entrySet()) {

               String findMap = mapContains(cs.getKey());
               
               
                     
            }
            
        }
        
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

