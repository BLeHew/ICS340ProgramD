package course;

public class Course {
    private Character day; // day this course is currently assigned to
    private Integer semTaken;
    private Integer numConflicts = 0;
    public Integer getNumConflicts() {
        return numConflicts;
    }
    public void addConflict() {
        numConflicts++;
    }
    public void removeConflicts() {
        numConflicts = 0;
    }
    public void setNumConflicts(Integer numConflicts) {
        this.numConflicts = numConflicts;
    }
    public void setDay(Character day) {
        this.day = day;
    }
    public Integer getSemTaken() {
        return semTaken;
    }
    public void setSemTaken(int semTaken) {
        this.semTaken = semTaken;
    }
    @Override
    public String toString() {
        return numConflicts + " " +  day + "\t";
    }

    public Character getDayTaken() {
        return day;
    }


}
