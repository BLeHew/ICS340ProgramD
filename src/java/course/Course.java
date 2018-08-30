package course;
import java.util.*;

public class Course {
    private char day = '-'; // day this course is currently assigned to
    private String name;
    private Integer semTaken;

    private HashSet<String> conflicts;

    public Course(String name) {
        this.name = name;
        conflicts = new HashSet<>();
    }
    public void setDay(Character day) {
        this.day = day;
    }
    public String getName() {
        return name;
    }
    public int getNumConflicts() {
        return conflicts.size();
    }
    public int getSemTaken() {
        return semTaken;
    }
    public void setSemTaken(int semTaken) {
        this.semTaken = semTaken;
    }
    @Override
    public String toString() {
        return conflicts.size() + "-" + name + "\t" + day + "\t";
    }

    public char getDayTaken() {
        return day;
    }






}
