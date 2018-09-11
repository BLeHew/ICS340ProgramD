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
        
        for(String str : constraintsMap.keySet()) {
            put(str,new CourseRequisites(str));
        }
        
        for(Entry<String,HashMap<String,String>> e : constraintsMap.entrySet()) {
                       
            for(Entry<String,String> cs : e.getValue().entrySet()) {

                    if(constraintsMap.containsKey(cs.getKey())) {
                        get(cs.getKey()).addConstraint(e.getKey(), cs.getKey());
                    }else {
                        get(e.getKey()).addConstraint(e.getKey(),cs.getKey());
                    }
                    //get(e.getKey()).addConstraint(e.getKey(), cs.getKey());
                
                     
            }
            
        }
        
    }
    
    
    public String treesContains(String lhs) {
        for(Entry<String,CourseRequisites> e : this.entrySet()) {
            if(e.getValue().contains(lhs)) {
                return e.getValue().getHead();
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

