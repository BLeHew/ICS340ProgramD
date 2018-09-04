package course;

public class Course {
    private char day = '-'; // day this course is currently assigned to
    private String name;
    private Integer semTaken;

    public Course(String name) {
        this.name = name;
    }
    public void setDay(Character day) {
        this.day = day;
    }
    public String getName() {
        return name;
    }
    public int getSemTaken() {
        return semTaken;
    }
    public void setSemTaken(int semTaken) {
        this.semTaken = semTaken;
    }
    @Override
    public String toString() {
        return name + "\t" + day + "\t";
    }

    public char getDayTaken() {
        return day;
    }

}
