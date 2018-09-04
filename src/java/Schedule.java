import java.util.*;

import course.*;

public class Schedule {
    private int numConflicts;

    protected Random r;

    protected final Courses courses;

    private static final long SEED = 9;

    public Schedule(Courses courses) {

        r = new Random();
        this.courses = courses;

    };
    public Schedule(Courses courses, int n) {
        this.courses = courses;
        this.courses.setSemTaken("NSci204",3);
        this.courses.setSemTaken("RelS304",0);
        this.courses.setSemTaken("ICS232",7);
        this.courses.setSemTaken("Math320",4);
        this.courses.setSemTaken("Chem105",3);
        this.courses.setSemTaken("ICS311",6);
        this.courses.setSemTaken("ICS499",10);
        this.courses.setSemTaken("Phys110",1);
        this.courses.setSemTaken("EthS100",1);
        this.courses.setSemTaken("Comm320",4);
        this.courses.setSemTaken("ICS372",9);
        this.courses.setSemTaken("ICS471",9);
        this.courses.setSemTaken("Math120",0);
        this.courses.setSemTaken("Psyc100",7);
        this.courses.setSemTaken("Writ231",6);
        this.courses.setSemTaken("Writ131",0);
        this.courses.setSemTaken("ICS365",10);
        this.courses.setSemTaken("Math210",1);
        this.courses.setSemTaken("Math211",2);
        this.courses.setSemTaken("Math310",9);
        this.courses.setSemTaken("Math215",2);
        this.courses.setSemTaken("Lit100",5);
        this.courses.setSemTaken("ICS140",3);
        this.courses.setSemTaken("ICS382",10);
        this.courses.setSemTaken("ICS141",4);
        this.courses.setSemTaken("ICS240",5);
        this.courses.setSemTaken("ICS460",8);
        this.courses.setSemTaken("ICS340",6);
        this.courses.setSemTaken("ICS440",7);
        this.courses.setSemTaken("ICS462",8);

    }

    public Courses getCourses(){
        return courses;
    }
    public boolean hasBadCourse() {
        return courses.hasBadCourse();
    }
    public void changeCourseSemester(String course, int sem) {
        courses.changeCourseSemester(course, sem);
    }
    public void validateDays() {
        courses.validateDays();
    }
    public void assignSemesters(long seed) {
        int semester;
        r.setSeed(seed);
        for(Course c : courses.values()) {
            semester = r.nextInt(11);
            courses.setSemTaken(c.getName(),semester);
        }

    }
    public void print() {
        StringBuilder[] output = new StringBuilder[11];
        for(int i = 0; i < output.length; i++) {
            output[i] = new StringBuilder();
        }
        for(Course c : courses.values()) {
             output[c.getSemTaken()].append(courses.getCourseDomain(c.getName()).size(c.getSemTaken()) + " " + c.toString());
        }
        for(int i = 0; i < 11; i++) {
            System.out.println(i + ". " + output[i]);
        }
    }




}
