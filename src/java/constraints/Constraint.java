package constraints;

public class Constraint {
    private final String type;
    private final String rhs;
    private final String lhs;
    
    public String getType() {
        return type;
    }

    public String getRhs() {
        return rhs;
    }

    public Constraint(String lhs, String type, String rhs) {
        this.lhs = lhs;
        this.type = type;
        this.rhs = rhs;
    }
    public Constraint(String type, String rhs) {
        lhs = null;
        this.type = type;
        this.rhs = rhs;
    }
    public Constraint(String lhs) {
        this.lhs = lhs;
        type = null;
        rhs = null;
    }
    public String getLhs() {
        return lhs;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (lhs == null) ? 0 : lhs.hashCode());
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
        Constraint other = (Constraint) obj;
        if ( lhs == null ) {
            if ( other.lhs != null )
                return false;
        } else if ( !lhs.equals(other.lhs) )
            return false;
        return true;
    }

    @Override
    public String toString() {
        return lhs + " " + type + " " + rhs;
    }

}
