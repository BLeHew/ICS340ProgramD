import java.util.*;

public class Schedule {
    private int numConflicts;

    private Semester[] sems = new Semester[11];

    private Random r;

    private Courses courses;

    private static final long SEED = 1;

    public Schedule(Courses courses) {

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
    public Schedule(Courses courses,int m) {
        r = new Random();
        for(int i = 0; i < sems.length ; i++) {
            sems[i] = new Semester(i);
        }
        this.courses = courses;
        for(Course c: courses.values()) {
            switch(c.getName()) {
            case "NSci204": c.setSemTaken(3);
                break;
            case "RelS304": c.setSemTaken(0);
                break;
            case "ICS232": c.setSemTaken(7);
                break;
            case "Math320": c.setSemTaken(4);
                break;
            case "Chem105": c.setSemTaken(3);
                break;
            case "ICS311": c.setSemTaken(6);
                break;
            case "ICS499": c.setSemTaken(10);
                break;
            case "Phys110": c.setSemTaken(1);
                break;
            case "EthS100": c.setSemTaken(1);
                break;
            case "Comm320": c.setSemTaken(4);
                break;
            case "ICS372": c.setSemTaken(9);
                break;
            case "ICS471": c.setSemTaken(9);
                break;
            case "Math120": c.setSemTaken(0);
                break;
            case "Psyc100": c.setSemTaken(7);
                break;
            case "Writ231": c.setSemTaken(6);
                break;
            case "Writ131": c.setSemTaken(1);
                break;
            case "ICS365": c.setSemTaken(10);
                break;
            case "Math210": c.setSemTaken(0);
                break;
            case "Math211": c.setSemTaken(2);
                break;
            case "Math310": c.setSemTaken(9);
                break;
            case "Math215": c.setSemTaken(2);
                break;
            case "Lit100": c.setSemTaken(5);
                break;
            case "ICS140": c.setSemTaken(3);
                break;
            case "ICS382": c.setSemTaken(10);
                break;
            case "ICS141": c.setSemTaken(4);
                break;
            case "ICS240": c.setSemTaken(5);
                break;
            case "ICS460": c.setSemTaken(8);
                break;
            case "ICS340": c.setSemTaken(6);
                break;
            case "ICS440": c.setSemTaken(7);
                break;
            case "ICS462": c.setSemTaken(8);
                break;
            }
            sems[c.getSemTaken()].add(c);

        }
        for(Semester s : sems) {
            s.assignDaysToCourses();
        }
        //courses.assignDays(r);
        addUpConflicts();

    }
    public Courses getCourses() {
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
