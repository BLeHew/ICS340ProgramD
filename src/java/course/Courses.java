package course;
import java.io.File;
import java.util.*;
import java.util.Map.Entry;

import conflict.*;
import constraints.*;
import fitness.CoursesFitness;
import schedule.*;


public class Courses extends HashMap<String,Course> {

    private HashSet<String> coursesWithConflicts;
    
    private Semester[] sems = new Semester[11];

    public static final double CONSCONFLICT = .999;
    public static final double DAYCONFLICT = .99999;
    public static final double NOCONFLICT = 1.000001;
    public static final double DOMAINCONFLICT = 0;

    public Courses(HashMap<String,Course> courses) {
        this.putAll(courses);
        for(int i = 0; i < 11; i++) {
            sems[i] = new Semester(i);
        }
    }
    public void assignSemesters() {
        int semester;
        Random r = new Random();
        
        for(Entry<String, Course> c : entrySet()) {
            semester = r.nextInt(11);
            setSemTaken(c.getKey(),semester);
        }

    }
    public void setSemTaken(String course, int sem) {

       if(get(course).getSemTaken() != null) {
            sems[get(course).getSemTaken()].remove(course);
            if(sems[get(course).getSemTaken()].getAssignedDays() != null) {
                getAssignedDaysFromSemesters(get(course).getSemTaken());
            }
        }

       get(course).setSemTaken(sem);
       sems[sem].add(course,CourseSchedules.getInstance().get(course));

        
       if(sems[sem].getAssignedDays() != null) {
            getAssignedDaysFromSemesters(sem);
        }

    }
    private ArrayList<Integer> getNonFullSemesters() {
        ArrayList<Integer> nonFullSemesters = new ArrayList<Integer>();

        for(int i = 0; i < sems.length; i++) {
            if(sems[i].canAddMoreDays()) {
                nonFullSemesters.add(i);
            }
        }
        return nonFullSemesters;
    }
    private void getAssignedDaysFromSemesters(int sem) {
        for(Entry<String,Character> e : sems[sem].getAssignedDays().entrySet()) 
            get(e.getKey()).setDay(e.getValue());      
    }
    public boolean hasConflicts() {
        return coursesWithConflicts.size() > 0;
    }
    public void checkConflicts() {
        coursesWithConflicts = new HashSet<String>();
        
        for(Entry<String,Course> course : entrySet()) {
            if(CourseConstraints.getInstance().hasConstraints(course.getKey())) {
                HashMap<String, String> courseConflicts = CourseConstraints.getInstance().getCourseConflicts(course.getKey());

                for(Entry<String,String> rhsCourse : courseConflicts.entrySet()) {
                    if(CourseConstraints.getInstance().coursesHaveConflict(
                        course.getKey(), 
                        course.getValue().getSemTaken(), 
                        rhsCourse.getKey(), 
                        get(rhsCourse.getKey()).getSemTaken())) {
                        
                        coursesWithConflicts.add(course.getKey());
                        coursesWithConflicts.add(rhsCourse.getKey());
                        CoursesFitness.getInstance().updateFitness(rhsCourse.getKey(), get(rhsCourse.getKey()).getSemTaken(), CONSCONFLICT);
                        CoursesFitness.getInstance().updateFitness(course, CONSCONFLICT);
                    }
                }
                
            }
            if(sems[course.getValue().getSemTaken()].hasTooManyDays()) {
                coursesWithConflicts.add(course.getKey());
                CoursesFitness.getInstance().updateFitness(course.getKey(),get(course.getKey()).getSemTaken(),DAYCONFLICT);
            }else if(course.getValue().getDayTaken() == null || course.getValue().getDayTaken() == '-') {
                coursesWithConflicts.add(course.getKey());
                CoursesFitness.getInstance().updateFitness(course.getKey(),get(course.getKey()).getSemTaken(),DAYCONFLICT);
            }
            if(!coursesWithConflicts.contains(course.getKey())) {
                CoursesFitness.getInstance().updateFitness(course.getKey(),get(course.getKey()).getSemTaken(),NOCONFLICT);
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
        int i = 1;
        while(!coursesWithConflicts.isEmpty()) {

            for(String course : coursesWithConflicts) {
               setSemTaken(course,
                   CoursesFitness.getInstance().getHealthiestSemester(
                       course,getNonFullSemesters()));
            }
            i++;
            checkConflicts();
        }

        
        
    }
    public void resetFitness() {
        CoursesFitness.getInstance().resetAllFitness();
        
    }



}
