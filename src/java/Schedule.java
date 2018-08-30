import java.util.*;

import course.*;

public class Schedule {
    private int numConflicts;

    protected Random r;

    protected final Courses courses;

    private static final long SEED = 2;

    public Schedule(Courses courses) {

        r = new Random();
        r.setSeed(SEED);
        this.courses = courses;
        assignSemesters();

    };
    public Schedule(Courses courses, int n) {
        r = new Random();
        this.courses = courses;
        for(Course c: this.courses.values()) {
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
            case "Writ131": c.setSemTaken(0);
                break;
            case "ICS365": c.setSemTaken(10);
                break;
            case "Math210": c.setSemTaken(1);
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

        }



    }
    public Schedule(Schedule other) {
        r = other.r;
        courses = other.courses;
    }
    public Courses getCourses(){
        return courses;
    }

    public void assignSemesters() {
        int semester;

        for(Course c : courses.values()) {
            semester = r.nextInt(11);
            c.setSemTaken(semester);
        }

    }
    public void print() {
        StringBuilder[] output = new StringBuilder[11];
        for(int i = 0; i < output.length; i++) {
            output[i] = new StringBuilder();
        }
        for(Course c : courses.values()) {
             output[c.getSemTaken()].append(c.toString());
        }
        for(int i = 0; i < 11; i++) {
            System.out.println(i + ". " + output[i]);
        }
    }




}
