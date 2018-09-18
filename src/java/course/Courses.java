package course;
import java.util.*;
import java.util.Map.Entry;

import conflict.*;
import constraints.*;
import fitness.CoursesFitness;
import schedule.*;


public class Courses extends HashMap<String,Course> {

    public static CourseSchedules courseSchedules = new CourseSchedules();
    private CourseConstraints courseConstraints = new CourseConstraints();
    private CoursesFitness coursesFitness = new CoursesFitness();
    
    private HashSet<String> coursesWithConflicts;
    
    private int numConflicts = 0;

    private Semester[] sems = new Semester[11];

    public static final long SEED = 200000;
    public static final Random rand = new Random();

    public Courses() {
        for(int i = 0; i < 11; i++) {
            sems[i] = new Semester(i);
        }
    }
    public void put(String c, String fallDays, String springDays, String summerDays) {
        put(c,new Course());
        courseSchedules.put(c, new CourseSchedule(fallDays, springDays, summerDays));
        coursesFitness.add(c);
    }

    public void put(String lhs, String type, String rhs) {
        courseConstraints.addConstraint(lhs, type, rhs);
    }
    public int getNumConflicts() {
        return numConflicts;
    }
    public void setSemTaken(String course, int sem) {

        if(get(course).getSemTaken() != null) {
            sems[get(course).getSemTaken()].remove(course);
            if(sems[get(course).getSemTaken()].getAssignedDays() != null) {
                getAssignedDaysFromSemesters(get(course).getSemTaken());
            }
        }

        get(course).setSemTaken(sem);
        
      
        sems[sem].add(course,courseSchedules.get(course));
        
        if(sems[sem].getAssignedDays() != null) {
            getAssignedDaysFromSemesters(sem);
        }

    }
    private void getAssignedDaysFromSemesters(int sem) {
        for(Entry<String,Character> e : sems[sem].getAssignedDays().entrySet()) {
            get(e.getKey()).setDay(e.getValue());
        }
    }
    public boolean hasConflicts() {
        return coursesWithConflicts.size() > 0;
    }
    public void printFitness() {
        System.out.println(coursesFitness);
    }
    public void checkConflicts() {
        Collection<String> coursesWithConstraints = courseConstraints.getCoursesWithConflicts();
        coursesWithConflicts = new HashSet<String>();
        
        for(Entry<String,Course> c : entrySet()) {
            c.getValue().removeConflicts();
            if(sems[c.getValue().getSemTaken()].hasTooManyDays()) {
                c.getValue().addConflict();
                coursesWithConflicts.add(c.getKey());
                coursesFitness.updateFitness(c.getKey(), c.getValue().getSemTaken(), 1);
            }
        }
        
        for(String course : coursesWithConstraints) {
            
            HashMap<String, String> courseConflicts = courseConstraints.getCourseConflicts(course);
            
            for(Entry<String,String> e : courseConflicts.entrySet()) {
                
                switch(e.getValue()) {
                    case "<" : 
                        if(get(course).getSemTaken() >= get(e.getKey()).getSemTaken()) { 
                            get(course).addConflict(); 
                            get(e.getKey()).addConflict();
                            coursesFitness.updateFitness(course, get(course).getSemTaken(), 1);
                            coursesFitness.updateFitness(e.getKey(), get(e.getKey()).getSemTaken(), 1);
                            coursesWithConflicts.add(course);
                            coursesWithConflicts.add(e.getKey());
                        break;
                    }   
                    case "<=": 
                        if(get(course).getSemTaken() > get(e.getKey()).getSemTaken()) { 
                            get(course).addConflict(); 
                            get(e.getKey()).addConflict();
                            coursesFitness.updateFitness(course, get(course).getSemTaken(), 1);
                            coursesFitness.updateFitness(e.getKey(), get(e.getKey()).getSemTaken(), 1);
                            coursesWithConflicts.add(course);
                            coursesWithConflicts.add(e.getKey());
                        break;
                    }
                        
                }
            }
        }
        
        for(Entry<String,Course> c : entrySet()) {
            if(c.getValue().getDayTaken() == null || c.getValue().getDayTaken() == '-') {
                c.getValue().addConflict();
                coursesFitness.updateFitness(c.getKey(), get(c.getKey()).getSemTaken(), 1);
                coursesWithConflicts.add(c.getKey());
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder[] semOutput = new StringBuilder[11];
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < semOutput.length; i++) {
            semOutput[i] = new StringBuilder();
        }
        for(Entry<String, Course> c : entrySet()) {
            semOutput[c.getValue().getSemTaken()].append(c.getKey() + "\t" + c.getValue());
        }
        for(int i = 0; i < 11; i++) {
            output.append(i + ". " + semOutput[i] + "\n");
        }
        return output.toString();
    }
    public void solve() {
        
        int i = 0;
        while(!coursesWithConflicts.isEmpty()) {
            if(i % 100000 == 0) {
                printFitness();
                System.out.println(this);
            }
            for(String c : coursesWithConflicts) {
                int nextSem = coursesFitness.getHealthiestSemester(c);
                setSemTaken(c,nextSem);   
            }
            checkConflicts();
            i++;
        }
        
        
    }



}
