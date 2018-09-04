package domain;

import java.util.*;

import conflict.*;

public class CourseDomain {

    public static final int SIZE = 11;


    private ArrayList<HashSet<Conflict>> conflicts = new ArrayList<>(11);

    public CourseDomain(String fallDays,String springDays, String summerDays) {



        for(int i = 0; i < SIZE; i++) {
            conflicts.add(new HashSet<Conflict>());
        }

        if(fallDays.charAt(0) == '-') {
            addDaysConflict(0);
        }
        if(springDays.charAt(0) == '-') {
            addDaysConflict(1);
        }
        if(summerDays.charAt(0) == '-') {
            addDaysConflict(2);
        }

    }
    public void removeConflict(Conflict c) {
        for(HashSet<Conflict> hs : conflicts) {
            hs.remove(c);
        }
    }
    public int size(int index) {
        return conflicts.get(index).size();
    }
    public void addSemesterConflict(int sem) {
        conflicts.get(sem).add(new Conflict(1));
    }
    public void addConflict(int sem, Conflict c) {
        conflicts.get(sem).add(c);
    }
    private void addDaysConflict(int start) {
        for(int i = start; i < SIZE; i+= 3) {
            conflicts.get(i).add(new Conflict(3));
        }
    }

}
