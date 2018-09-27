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
    public Courses(Courses courses) {
        this.putAll(courses);
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
            sems[get(course).getSemTaken()].remove(get(course));
            if(sems[get(course).getSemTaken()].hasRightAmountOfDays()) {
                getAssignedDaysFromSemesters(get(course).getSemTaken());
            }
        }

       get(course).setSemTaken(sem);
       sems[sem].add(get(course));

        
       if(sems[sem].hasRightAmountOfDays()) {
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
        for(Entry<String,Course> e : sems[sem].getSemCourses().entrySet()) 
            get(e.getKey()).setDay(e.getValue().getDayTaken());      
    }
    public boolean hasConflicts() {
        return coursesWithConflicts.size() > 0;
    }
    public void checkConflicts() {
        coursesWithConflicts = new HashSet<String>();
        
        for(Course course : values()) {
            if(CourseConstraints.getInstance().hasConstraints(course)) {
                HashMap<String, String> courseConflicts = CourseConstraints.getInstance().getCourseConflicts(course);

                for(Entry<String,String> rhsCourse : courseConflicts.entrySet()) {
                    if(CourseConstraints.getInstance().coursesHaveConflict(course, get(rhsCourse.getKey()))) {
                        
                        coursesWithConflicts.add(course.getName());
                        coursesWithConflicts.add(rhsCourse.getKey());
                        course.getFitness().update(course.getSemTaken(), CONSCONFLICT);
                        get(rhsCourse.getKey()).getFitness().update(get(rhsCourse.getKey()).getSemTaken(), CONSCONFLICT);
                    }
                }
                
            }
            if(sems[course.getSemTaken()].hasTooManyDays()) {
                coursesWithConflicts.add(course.getName());
                course.getFitness().update(course.getSemTaken(),DAYCONFLICT);
            }else if(course.getDayTaken() == null || course.getDayTaken() == '-') {
                coursesWithConflicts.add(course.getName());
                course.getFitness().update(course.getSemTaken(),DAYCONFLICT);
            }
            if(!coursesWithConflicts.contains(course.getName())) {
                course.getFitness().update(course.getSemTaken(),NOCONFLICT);
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        
        for(Semester s : sems) {
            output.append(s + "\n");
        }
        return output.toString();
    }
    public void solve() {
        int i = 1;
        while(!coursesWithConflicts.isEmpty()) {
            if(i % 10000 == 0) {
                System.out.println(i);
                System.out.println(this);
            }
            for(String course : coursesWithConflicts) {
               setSemTaken(course,get(course).getFitness().getHealthiestSemester(getNonFullSemesters()));
            }
            i++;
            checkConflicts();
        }

        
        
    }



}
