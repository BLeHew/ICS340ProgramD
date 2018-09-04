package conflict;

public class Conflict {
    // 1 is semester conflict
    // 2 is a course conflict
    // 3 is a day conflict
   // private String course;

    private int type;

    public Conflict(int type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + type;
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
        if ( type != other.type )
            return false;
        return true;
    }



}
