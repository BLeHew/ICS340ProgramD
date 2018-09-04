package course;
import java.util.*;

import domain.*;

public class Semester {
    private int numDaysTaken;
    private SemesterDomain domain;
    private final int semNum;
    private HashSet<String> courseList;
    private HashMap<String, Character> courseDays = new HashMap<String, Character>();
    private HashSet<String> badCourses;

    public Semester(int semNum) {
        domain  = new SemesterDomain(semNum);
        numDaysTaken = 0;
        this.semNum = semNum;
        courseList = new HashSet<>();
    }

    public void add(String course,Courses courses) {
        numDaysTaken++;
        domain.add(course, courses.getCourseSchedule(course));
        courseList.add(course);
        
        if(courses.getCourseSchedule(course).endsWith("O")) {
            courses.get(course).setDay('O');
            courseDays.put(course, 'O');
        }else {
            courseDays.put(course, courses.getCourseSchedule(course).charAt(0));
            
        }

        if(domain.hasEnoughDays(numDaysTaken)) {
            assignDays(courses);
        }

        if(!domain.isNumDaysValid(numDaysTaken)) {
            //courses.getCourseDomain(course).addConflict(semNum);
        }

    }
    public void assignDays(Courses courses) {
        
        for(String course : courseList) {
            if(courses.getCourseSchedule(course).endsWith("O")) {
                courses.get(course).setDay('O');
                domain.remove('O');
            }else if(domain.isEmpty()) {
                courses.get(course).setDay('-');
            }
            else {
                courses.get(course).setDay(domain.getNextDay(courses.getCourseSchedule(course), course));
            }
        }

    }
    public void remove(String course,Courses courses) {
        courseList.remove(course);
        domain.remove(course,courses);
        numDaysTaken--;
    }

    public boolean checkIfValid(String course, Courses courses) {
        domain.pool();
        courses.get(course).setDay(domain.getNextDay(courses.getCourseSchedule(course), course));
        if(courses.get(course).getDayTaken() == '-') {
            return false;
        }
        return true;
    }
}
