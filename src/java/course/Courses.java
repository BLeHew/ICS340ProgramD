package course;
import java.io.File;
import java.util.*;
import java.util.Map.Entry;

import conflict.CoursesWithConflicts;
import constraints.*;
import fitness.CoursesFitness;
import schedule.*;


public class Courses extends HashMap<String,Course> {

    private CoursesWithConflicts coursesWithConflicts;
    
    private Semester[] sems = new Semester[11];

    public static final double CONSCONFLICT = .99;
    public static final double DAYCONFLICT = .9999;
    public static final double NOCONFLICT = 1.0001;
    public static final double DOMAINCONFLICT = 0;

    public Courses(HashMap<String,Course> courses) {
        coursesWithConflicts = new CoursesWithConflicts();
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
        if(get(course).hasConflictingCourses()){
            for(Entry<String,String> conflictingCourse : get(course).getConflictingCourses().entrySet()){
                if(get(conflictingCourse.getKey()).getSemTaken() != null) {
                    if (get(course).hasConflictWith(get(conflictingCourse.getKey()))) {
                        coursesWithConflicts.addConflict(course, conflictingCourse.getKey());
                        coursesWithConflicts.addConflict(conflictingCourse.getKey(), course);
                        get(course).getFitness().update(get(course).getSemTaken(), CONSCONFLICT);
                        get(conflictingCourse.getKey()).getFitness().update(get(conflictingCourse.getKey()).getSemTaken(), CONSCONFLICT);
                    } else {
                        coursesWithConflicts.removeConflict(course, conflictingCourse.getKey());
                        coursesWithConflicts.removeConflict(conflictingCourse.getKey(), course);
                        get(course).getFitness().update(get(course).getSemTaken(), NOCONFLICT);
                        get(conflictingCourse.getKey()).getFitness().update(get(conflictingCourse.getKey()).getSemTaken(), NOCONFLICT);
                    }
                }
            }
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
    public void checkConflicts() {
        for (Course course : values()) {

            if (sems[course.getSemTaken()].hasTooManyDays() || course.getDayTaken() == null || course.getDayTaken() == '-') {
                coursesWithConflicts.addConflict(course.getName(),"DAY");
                course.getFitness().update(course.getSemTaken(), DAYCONFLICT);
            }else {
                coursesWithConflicts.removeConflict(course.getName(),"DAY");
                course.getFitness().update(course.getSemTaken(), NOCONFLICT);
            }
            if (!coursesWithConflicts.contains(course.getName())) {
                course.getFitness().update(course.getSemTaken(), NOCONFLICT);
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        
        for(Semester s : sems) {
            output.append(s);
            output.append("\n");
        }
        return output.toString();
    }
    @Override
    public int hashCode() {

        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(sems);
        return result;
    }

    public void solve() {
        int i = 1;

        while(!coursesWithConflicts.isEmpty()) {
            if(i % 1000 == 0) {
                assignSemesters();
                System.out.println("Resetting");
            }
            HashSet<String> c = new HashSet<>(coursesWithConflicts.keySet());
            for(String course : c) {
                int nextSem = get(course).getFitness().getHealthiestSemester(getNonFullSemesters());
                setSemTaken(course,nextSem);
            }
            i++;
            checkConflicts();
        }

        
        
    }



}
