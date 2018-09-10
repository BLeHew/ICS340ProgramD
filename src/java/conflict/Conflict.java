package conflict;

public class Conflict {
    // 1 is semester conflict
    // 2 is a course conflict
    // 3 is a day conflict
   // private String course;

    private final String type;
    private final String course;
    private final int semester;
    
    public Conflict(String type, String course,int semester) {
        this.type = type;
        this.course = course;
        this.semester = semester;
    }
    public Conflict(Conflict conflict, int semester) {
        this.type = conflict.type;
        this.course = conflict.course;
        this.semester = semester;
    }
    public String getType() {
        return type;
    }


    public String getCourse() {
        return course;
    }


    public int getSemester() {
        return semester;
    }


    @Override
    public String toString() {
        if(course == null) {
            return "Type: " + type;
        }else
            return "Type: " + type + " Course: " + course + " Sem: " + semester;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (course == null) ? 0 : course.hashCode());
        result = prime * result + semester;
        result = prime * result + ( (type == null) ? 0 : type.hashCode());
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
        Conflict other = (Conflict) obj;
        if ( course == null ) {
            if ( other.course != null )
                return false;
        } else if ( !course.equals(other.course) )
            return false;
        if ( semester != other.semester )
            return false;
        if ( type == null ) {
            if ( other.type != null )
                return false;
        } else if ( !type.equals(other.type) )
            return false;
        return true;
    }






}
