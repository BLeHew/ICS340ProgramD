package course;
import java.io.File;
import java.util.*;
import java.util.Map.Entry;

import conflict.*;
import constraints.*;
import fitness.CoursesFitness;
import schedule.*;


public class Courses extends HashMap<String,Course> {

    public static CourseSchedules courseSchedules = new CourseSchedules();
    private CourseConstraints courseConstraints = new CourseConstraints();
    //private CoursesFitness coursesFitness = new CoursesFitness(new File("courseFitness"));
    private CoursesFitness coursesFitness = new CoursesFitness();
    
    private HashSet<String> coursesWithConflicts;
    
    private int numConflicts = 0;

    private Semester[] sems = new Semester[11];

    public static final long SEED = 200000;
    public static final Random rand = new Random();
    
    public static final double CONSCONFLICT = .99;
    public static final double DAYCONFLICT = .999;
    public static final double DOMAINCONFLICT = 0;

    public Courses() {
        for(int i = 0; i < 11; i++) {
            sems[i] = new Semester(i);
        }
    }
    public void put(String c, String fallDays, String springDays, String summerDays) {
        put(c,new Course());
        courseSchedules.put(c, new CourseSchedule(fallDays, springDays, summerDays));
        coursesFitness.add(c,courseSchedules.get(c).getDashes());
        
    }

    public void put(String lhs, String type, String rhs) {
        courseConstraints.addConstraint(lhs, type, rhs);
        if(type.equals("<")) {
            coursesFitness.updateFitness(lhs, 10, DOMAINCONFLICT);
            coursesFitness.updateFitness(rhs, 0, DOMAINCONFLICT);
        }
    }
    public int getNumConflicts() {
        return numConflicts;
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
       sems[sem].add(course,courseSchedules.get(course));

        
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
        for(Entry<String,Character> e : sems[sem].getAssignedDays().entrySet()) {
            get(e.getKey()).setDay(e.getValue());
        }
    }
    public boolean hasConflicts() {
        return coursesWithConflicts.size() > 0;
    }
    public void checkConflicts() {
        coursesWithConflicts = new HashSet<String>();
        
        for(Entry<String,Course> c : entrySet()) {
            c.getValue().removeConflicts();
            if(courseConstraints.hasConstraints(c.getKey())) {
                HashMap<String, String> courseConflicts = courseConstraints.getCourseConflicts(c.getKey());

                for(Entry<String,String> e : courseConflicts.entrySet()) {
                        if(e.getValue().equals("<") && (get(c.getKey()).getSemTaken() >= get(e.getKey()).getSemTaken())) {
                            
                            get(c.getKey()).addConflict();
                            get(e.getKey()).addConflict();
                            coursesWithConflicts.add(c.getKey());
                            coursesWithConflicts.add(e.getKey());
                            coursesFitness.updateFitness(e.getKey(), get(e.getKey()).getSemTaken(), CONSCONFLICT);
                        }
                        if(e.getValue().equals("<=") && (get(c.getKey()).getSemTaken() > get(e.getKey()).getSemTaken())) {
                            get(c.getKey()).addConflict();
                            get(e.getKey()).addConflict();
                            coursesWithConflicts.add(c.getKey());
                            coursesWithConflicts.add(e.getKey());
                            coursesFitness.updateFitness(e.getKey(), get(e.getKey()).getSemTaken(), CONSCONFLICT);
                        }
                }
                
            }
            boolean addDayConflict = false;
            if(sems[c.getValue().getSemTaken()].hasTooManyDays()) {
                addDayConflict = true;
            }
            if(c.getValue().getDayTaken() == null || c.getValue().getDayTaken() == '-') {
                addDayConflict = true;
            }
            if(addDayConflict) {
                get(c.getKey()).addConflict();
                coursesWithConflicts.add(c.getKey());
                coursesFitness.updateFitness(c.getKey(),get(c.getKey()).getSemTaken(),DAYCONFLICT);
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
        long msStart = System.currentTimeMillis();
        System.out.println(this);
        while(!coursesWithConflicts.isEmpty()) {
            for(String c : coursesWithConflicts) {
                int nextSem;
                if(getNonFullSemesters().size() > 0) {
                    nextSem = coursesFitness.getHealthiestSemester(c,getNonFullSemesters());
                }else {
                    nextSem = coursesFitness.getHealthiestSemester(c);
                }
                
                setSemTaken(c,nextSem);   
            }
            i++;
            checkConflicts();
        }
        
        long msEnd = System.currentTimeMillis();

        System.out.println("Total time taken: " + (msEnd - msStart));
        System.out.println(i);
        System.out.println(this);
        System.out.println(coursesFitness);
        checkConflicts();
        System.out.println(this);
        
        
    }



}
