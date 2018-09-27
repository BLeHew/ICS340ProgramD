package course;
import java.util.*;
import java.util.Map.*;

import schedule.*;

public class Semester {
    private final int semNum;
    private HashSet<Course> coursesToCheck;
    private HashSet<Character> daysTaken;

    //private HashMap<String,Character> assignedDays;
    //private HashMap<String,CourseSchedule> courseSchedules = new HashMap<>();
    
    private HashMap<String, Course> semCourses = new HashMap<>();
    
    private int numTries;

    public Semester(int semNum) {
        this.semNum = semNum;
        
    }
    public HashMap<String, Course> getSemCourses(){
        return semCourses;
    }
    public boolean hasRightAmountOfDays() {
        return semCourses.size() == rightAmountOfDays();
    }
    public boolean hasTooManyDays() {
        return semCourses.size() > rightAmountOfDays();
    }
    public boolean canAddMoreDays() {
        return semCourses.size() < rightAmountOfDays();
    }
    public void add(Course c) {
        semCourses.put(c.getName(),c);

        if(semCourses.size() == rightAmountOfDays()) {
            assignDays();
        }
        

    }
    public void assignDays() {

        numTries = 1;
        daysTaken = new HashSet<>();
        coursesToCheck = new HashSet<Course>();

        
        for(Course c : semCourses.values()) {
            if(c.getSchedule().hasOnline(semNum)) {
                c.setDay('O');
            }else if(c.getSchedule().length(semNum) == 1) {
                
                        if(daysTaken.contains(c.getSchedule().charAt(semNum,0))) {
                             c.setDay('-');
                        }else {
                             daysTaken.add(c.getSchedule().charAt(semNum,0));
                             c.setDay(c.getSchedule().charAt(semNum,0));
                        }

            }else {
                numTries *= c.getSchedule().length(semNum);
                coursesToCheck.add(c);

            }


        }
        if(coursesToCheck.size() == 0) {
            return;
        }
        while(daysTaken.size() < rightAmountOfDays()  && numTries > 0) {

            for(Course course : coursesToCheck) {

                for(int i = 0; i < course.getSchedule().getDays(semNum).length(); i++) {
                    Character day = course.getSchedule().getDayFromSchedule(semNum,i);
                    if(!daysTaken.contains(day)){
                        daysTaken.add(day);
                        semCourses.get(course.getName()).setDay(day);
                        break;
                    }
                    semCourses.get(course.getName()).setDay('-');
                }
                
                numTries--;
        }
            

        }
        


    }
    public int numCourses() {
        return semCourses.size();
    }
    public void remove(Course course) {

        semCourses.remove(course.getName());

        if(semCourses.size() == rightAmountOfDays()) { 
            assignDays();
        } 

    }
    public int rightAmountOfDays() {
          if(semNum % 3 == 0 || (semNum + 2) % 3 == 0 ) {
              return 3;
          }else
              return 2;
      }
    public int getSemNum() {
        return semNum;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(semNum + ". ");
        
        for(Course c : semCourses.values()) {
            output.append(c.getName() + "\t" + c.getDayTaken() + "\t");
        }
        return output.toString();
        
    }
}