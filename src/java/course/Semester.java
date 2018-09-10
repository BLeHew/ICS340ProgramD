package course;
import java.util.*;

import domain.*;

public class Semester {
    private final int semNum;
    private HashSet<String> courseList;
    private HashSet<String> coursesToCheck;
    private HashSet<Character> daysTaken = new HashSet<Character>();
    
    private int numTries;
    
    public Semester(int semNum) {
        this.semNum = semNum;
        courseList = new HashSet<String>();
    }
    public boolean contains(String course) {
        return courseList.contains(course);
    }
    public void add(String course,Courses courses) {
        courseList.add(course);
               
        if(courseList.size() == rightAmountOfDays()) {
            assignDays(courses);
        }

    }
    public void assignDays(Courses courses) {
        
        
        numTries = 1;
        daysTaken = new HashSet<Character>();
        coursesToCheck = new HashSet<String>();
        
        for(String course : courseList) {
            
            if(courses.getCourseSchedule(course).endsWith("O")) {
                courses.get(course).setDay('O');
            }else if(courses.getCourseSchedule(course).length() == 1) {
                    courses.get(course).setDay(courses.getCourseSchedule(course).charAt(0));
                        if(daysTaken.contains(courses.get(course).getDayTaken())) {
                             daysTaken.add('-');
                        } else {
                             daysTaken.add(courses.get(course).getDayTaken());
                        }
                            
            }else {
                numTries *= courses.getCourseSchedule(course).length();
                coursesToCheck.add(course);
                
            }            
            
        } 
        
        if(daysTaken.contains('-') || coursesToCheck.size() == 0) {
            return;
        }
        while(daysTaken.size() < rightAmountOfDays()  && numTries > 0) {
            
            for(String course : coursesToCheck) {  
                
                for(int i = 0; i < courses.getCourseSchedule(course).length(); i++) {
                    Character day = courses.getDayFromSchedule(course, i);
                    if(!daysTaken.contains(day)){
                        daysTaken.add(day);
                        courses.get(course).setDay(day);
                        break;
                    }   

            } 
            numTries--;
        }
       
        }
        
        
    }

    public void remove(String course,Courses courses) {
        

        courseList.remove(course);
        
        if(courseList.size() == rightAmountOfDays()) {
            assignDays(courses);
        }

        
    }
    public int rightAmountOfDays() {
          if(semNum % 3 == 0 || (semNum + 2) % 3 == 0 ) {
              return 3;
          }else
              return 2;
      }

}
