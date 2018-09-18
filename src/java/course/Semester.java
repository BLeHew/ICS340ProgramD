package course;
import java.util.*;
import java.util.Map.*;

import schedule.*;

public class Semester {
    private final int semNum;
    private HashSet<String> coursesToCheck;
    private HashSet<Character> daysTaken;

    private HashMap<String,Character> assignedDays;
    private HashMap<String,CourseSchedule> courseSchedules = new HashMap<>();

    private int numTries;

    public Semester(int semNum) {
        this.semNum = semNum;
        
    }
    public HashMap<String,Character> getAssignedDays(){
        return assignedDays;
    }
    public boolean hasRightAmountOfDays() {
        return courseSchedules.size() == rightAmountOfDays();
    }
    public boolean hasTooManyDays() {
        return courseSchedules.size() > rightAmountOfDays();
    }
    public boolean canAddMoreDays() {
        return courseSchedules.size() < rightAmountOfDays();
    }
    public void resetAssignedDays() {
        assignedDays = null;
    }
    public boolean contains(String course) {
        return courseSchedules.containsKey(course);
    }
    public void add(String course,CourseSchedule schedule) {
        courseSchedules.put(course,schedule);

        if(courseSchedules.size() == rightAmountOfDays()) {
            assignDays();
        }
        

    }
    public void assignDays() {

        assignedDays = new HashMap<String,Character>();
        
        numTries = 1;
        daysTaken = new HashSet<>();
        coursesToCheck = new HashSet<>();

        
        for(Entry<String,CourseSchedule> course : courseSchedules.entrySet()) {
            if(course.getValue().hasOnline(semNum)) {
                assignedDays.put(course.getKey(),'O');
            }else if(course.getValue().length(semNum) == 1) {
                
                        if(daysTaken.contains(course.getValue().charAt(semNum,0))) {
                             assignedDays.put(course.getKey(), '-');
                        }else {
                             daysTaken.add(course.getValue().charAt(semNum,0));
                             assignedDays.put(course.getKey(),course.getValue().charAt(semNum,0));
                        }

            }else {
                numTries *= course.getValue().length(semNum);
                coursesToCheck.add(course.getKey());

            }


        }
        if(coursesToCheck.size() == 0) {
            return;
        }
        while(daysTaken.size() < rightAmountOfDays()  && numTries > 0) {

            for(String course : coursesToCheck) {

                for(int i = 0; i < courseSchedules.get(course).length(semNum); i++) {
                    Character day = courseSchedules.get(course).getDayFromSchedule(semNum,i);
                    if(!daysTaken.contains(day)){
                        daysTaken.add(day);
                        assignedDays.put(course,day);
                        break;
                    }
                    assignedDays.put(course, '-');
                }
                
                numTries--;
        }
            

        }
        


    }
    public int numCourses() {
        return courseSchedules.size();
    }
    public void remove(String course) {

        courseSchedules.remove(course);
        assignedDays = null;

        if(courseSchedules.size() == rightAmountOfDays()) { 
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

}