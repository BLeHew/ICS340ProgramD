package domain;

import java.util.*;

import conflict.*;
import course.Courses;

public class CourseDomain {

    public static final int SIZE = 11;
    private HashSet<Integer> initialDomain = new HashSet<Integer>();

    //private ArrayList<HashSet<Conflict>> conflicts = new ArrayList<>(11);
    private HashSet<Conflict> conflicts = new HashSet<Conflict>();
    
    public boolean hasConflicts() {
       return conflicts.size() > 0;
    }
    public void createInitial(String course) {
        for(int i = 0; i < SIZE; i++) {
            initialDomain.add(i);
        }
        ArrayList<Integer> semsToTrim = Courses.courseSchedules.get(course).schedsWithDash();
        
        if(semsToTrim != null) {
            for(Integer x : semsToTrim) {
                for(int i = x; i < SIZE; i+= 3) {
                    initialDomain.remove(i);
                }
            }  
        }
        //if(Courses.courseConstraints.hasNextConstraints(course)) {
        //    initialDomain.remove(11);
        //}

        
    }
    public void removeConflict(Conflict c) {
        conflicts.remove(c);
    }
    public int size(int index) {
        return conflicts.size();
    }
    public void addConflict(Conflict c) {
        conflicts.add(c);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Conflict c : conflicts) {
              sb.append(c + "\n");
        }
        return sb.toString();
    }
    public void addMultipleConflicts(Conflict conflict) {
        for(int i = conflict.getSemester(); i < SIZE; i++) {
            conflicts.add(new Conflict(conflict, i));
        }
    }
    public void removeMultipleConflicts(Conflict conflict) {
        while(conflicts.contains(conflict)) {
            conflicts.remove(conflict);
        }
    }


}
