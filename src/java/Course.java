import java.util.*;

public class Course {
    private char day; // day this course is currently assigned to
    private String name;
    private int semTaken;
    private int conflicts = 0;

    private String fallDays;   //days offered in the fall
    private String springDays; //days offered in the spring
    private String summerDays; //days offered in the summer


    private ArrayList<String> prereqs = new ArrayList<>(); //courses needed before this course
    private ArrayList<String> coreqs = new ArrayList<>(); //courses that require this course

    public Course(String name,
                  String fallDays,
                  String springDays,
                  String summerDays) {
        this.name = name;
        this.fallDays = fallDays;
        this.springDays = springDays;
        this.summerDays =  summerDays;

    }

    public Course() {};

    /*
    public Character getDayOfWeekOfSchedule(int dayNum) {
        return getSchedule().charAt(dayNum);
    }
    */
    public String getSchedule() {
        if(semTaken % 3 == 0) {
            return fallDays;
        }
        if((semTaken + 1) % 3 == 0) {
            return springDays;
        }
        if((semTaken + 2) % 3 == 0) {
            return summerDays;
        }
        /*
        switch(semTaken) {
            case 0:
            case 3:
            case 6:
            case 9: return fallDays;

            case 1:
            case 4:
            case 7:
            case 10: return springDays;

            case 2:
            case 5:
            case 8:
            case 11: return summerDays;
        }
        */
        return null;
    }
    public void setDay(Character day) {
        this.day = day;
    }
    public void setRandomDayFromSchedule(int num) {
        // c.setDay(c.getSchedule().charAt(r.nextInt(c.getSchedule().length())));
        day = getSchedule().charAt(num);
    }
    public String getName() {
        return name;
    }
    public void addConflict() {
        conflicts++;
    }
    public int getNumConflicts() {
        return conflicts;
    }
    public int getSemTaken() {
        return semTaken;
    }
    public void setSemTaken(int semTaken) {

        this.semTaken = semTaken;
    }
    public String getFallDays() {
        return fallDays;
    }

    public String getSpringDays() {
        return springDays;
    }

    public String getSummerDays() {
        return summerDays;
    }

    @Override
    public String toString() {
        return "Course: name =" + name + " " + fallDays + " " + springDays + " " + summerDays;
    }

    public void addPrereq(String preReq) {
        prereqs.add(preReq);
    }
    public void addCoreq(String coReq) {
        coreqs.add(coReq);
    }

    public char getDayTaken() {
        return day;
    }



}
