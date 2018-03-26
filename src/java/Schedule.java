import java.util.*;

public class Schedule {
    private int numConflicts;

    private Semester[] sems = new Semester[11];

    private Random r;

    private CourseList courses;

    private static final long SEED = 1;

    public Schedule(CourseList courses) {

        r = new Random();
        r.setSeed(SEED);

        for(int i = 0; i < sems.length ; i++) {
            sems[i] = new Semester(i);
        }
        this.courses = courses;
        assignSemesters();
        courses.assignDays(r);
        addUpConflicts();


    };
    public CourseList getCourses() {
        return courses;
    }
    public int getNumConflicts(Course c) {
        return courses.get(c.getName()).getNumConflicts();
    }
    public void changeCourseSemester(String c, int sem) {
        courses.get(c).setSemTaken(sem);

        numConflicts = 0;
        resetSemesters();
        courses.resetCourseConflicts();
        addUpConflicts();
    }
    public void changeCourseSemester(Course c, int sem) {
        changeCourseSemester(c.getName(),sem);
    }

    private void addUpConflicts() {
        checkIfDayIsValid();
        checkIfSemestersAreValid();
        checkIfSemesterDaysAreValid();
        checkConstraints();

    }
    private void checkIfDayIsValid() {
        numConflicts += courses.getDayConflicts();
    }
    private void checkConstraints() {
        numConflicts += courses.getConstraintConflicts();
    }
    private void checkIfSemesterDaysAreValid() {
        numConflicts += courses.getSemesterDaysConflicts();
    }


    public void resetSemesters() {
        for(int i = 0; i < sems.length ; i++) {
            sems[i] = new Semester(i);
        }
        for(Course c : courses.values()) {
            sems[c.getSemTaken()].add(c);
        }
    }


    private void checkIfSemestersAreValid() {
        for(int i = 0; i < sems.length; i++) {
            if(!sems[i].isNumDaysValid()) {
                for(Course c: courses.values()) {
                    if(c.getSemTaken() == i) {
                        c.addConflict();
                        numConflicts++;
                    }
                }
            }
        }
    }

    public int getNumConflicts() {
        return numConflicts;
    }

    public void assignSemesters() {
        int semester;
        for(Course c : courses.values()) {
            semester = r.nextInt(11);
            c.setSemTaken(semester);
            sems[semester].add(c);
        }

    }
    public void print() {
        String[] output = new String[11];
        for(int i = 0; i < output.length; i++) {
            output[i] = new String("");
        }
        for(Course c : courses.values()) {
             output[c.getSemTaken()] += c.toString();
        }
        for(int i = 0; i < output.length; i++) {
            System.out.println(i + ". " + output[i]);
        }
        System.out.println(numConflicts);
    }



}
