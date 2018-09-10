package course;

public class Course {
    private Character day; // day this course is currently assigned to
    private String name;
    private Integer semTaken;
    private boolean hasConflict;
    
    public Course(String name) {
        this.name = name;
    }
    public void setDay(Character day) {
        this.day = day;
    }
    public String getName() {
        return name;
    }
    public Integer getSemTaken() {
        return semTaken;
    }
    public void setSemTaken(int semTaken) {
        this.semTaken = semTaken;
    }
    @Override
    public String toString() {
        if(hasConflict) {
            return 1 + " \t" + name + "\t" + day + "\t";
        }
        else
            return 0 + " \t" + name + "\t" + day + "\t";
    }

    public Character getDayTaken() {
        return day;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        Course other = (Course) obj;
        if ( name == null ) {
            if ( other.name != null )
                return false;
        } else if ( !name.equals(other.name) )
            return false;
        return true;
    }
    public boolean hasConflict() {
        return hasConflict;
    }
    public void setConflict() {
        hasConflict = true;
    }
    public void removeConflicts() {
        hasConflict = false;
        
    }


}
