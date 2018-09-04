package domain;

import java.util.*;

import course.*;

public class SemesterDomain {

    private int semNum;
    private LinkedList<Character> dayPool;
    private HashSet<Character> domain;
    private HashSet<Character> daysTaken;
    
    private HashMap<String,HashSet<Character>> courses = new HashMap<String,HashSet<Character>>();
  
    public SemesterDomain(int semNum) {
        domain = new HashSet<>();
        dayPool = new LinkedList<>();
        daysTaken = new HashSet<>();
        this.semNum = semNum;
    }
    public void add(String course, String dayString) {
        if(!courses.containsKey(course)) {
            courses.put(course, new HashSet<Character>());
        }
        
        for(Character c : dayString.toCharArray()) {
            courses.get(course).add(c);
            domain.add(c);
            dayPool.add(c);
        }

    }
    
    public boolean isEmpty() {
        return domain.isEmpty();
    }
    public void remove(Character c) {
        domain.remove(c);
    }
    public void print() {
        System.out.println("Semester:" + semNum +"\t" + domain + "\t" + daysTaken);
    }
    public boolean isNumDaysValid(int numDaysTaken) {

        //semester is fall or spring
        if(semNum % 3 == 0 || (semNum + 2) % 3 == 0 ) {
            if(numDaysTaken > 3) {
                return false;
            }
        }
        //semester is summer
        if((semNum + 1) % 3 == 0) {
            if(numDaysTaken > 2) {
                return false;
            }
        }
        return true;
    }
    public boolean hasEnoughDays(int numDaysTaken) {
      //semester is fall or spring
        if(semNum % 3 == 0 || (semNum + 2) % 3 == 0 ) {
            if(numDaysTaken >= 3) {
                return true;
            }
        }
        //semester is summer
        if((semNum + 1) % 3 == 0) {
            if(numDaysTaken >= 2) {
                return true;
            }
        }
        return false;
    }
    public Character getNextDay(String sched,String course) {

        for(Character day : sched.toCharArray()) {

            if(!daysTaken.contains(day) && domain.contains(day)) {
                daysTaken.add(day);
                domain.remove(day);
                return day;
            }
        }

        return '-';
    }
    public void remove(String course, Courses courses) {
        for(Character c : courses.getCourseSchedule(course).toCharArray()) {
            dayPool.remove(c);
            domain.remove(c);
        }

        daysTaken.remove(courses.get(course).getDayTaken());

        for(Character c : dayPool) {
            domain.add(c);
        }

    }
    public void pool() {
        domain.addAll(dayPool);
    }
}
