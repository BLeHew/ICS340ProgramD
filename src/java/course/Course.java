package course;

public class Course {
    private Character day; // day this course is currently assigned to
    private Integer semTaken;
    private Integer numConflicts = 0;
    public void setDay(Character day) {
        this.day = day;
    }
    public void removeConflicts() {
        numConflicts = 0;
    }
    public void addConflict() {
        numConflicts++;
    }
    public Integer getSemTaken() {
        return semTaken;
    }
    public void setSemTaken(int semTaken) {
        this.semTaken = semTaken;
    }
    @Override
    public String toString() {
        return numConflicts + " " + day + "\t";
    }

    public Character getDayTaken() {
        return day;
    }


}
