package domain;

import java.util.*;

import course.*;

public class CourseDomain {

    public static final int SIZE = 11;


    private ArrayList<HashSet<Conflict>> conflicts = new ArrayList<>(11);

    public CourseDomain(String fallDays,String springDays, String summerDays) {

        for(int i = 0; i < SIZE; i++) {
            conflicts.add(new HashSet<Conflict>());
        }

        if(fallDays.charAt(0) == '-') {
            addDayConflict(0);
        }
        if(springDays.charAt(0) == '-') {
            addDayConflict(1);
        }
        if(summerDays.charAt(0) == '-') {
            addDayConflict(2);
        }

    }


    private void addDayConflict(int start) {
        for(int i = start; i < SIZE; i+= 3) {
            conflicts.get(i).add(new Conflict(3));
        }
    }

}
