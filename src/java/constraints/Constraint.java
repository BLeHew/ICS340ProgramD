package constraints;

public class Constraint {
    private final String type;
    private final String rhs;
    private final String lhs;
    
    public Constraint(String lhs, String type, String rhs) {
        this.lhs = lhs;
        this.type = type;
        this.rhs = rhs;
    }

    public String getType() {
        return type;
    }

    public String getRhs() {
        return rhs;
    }
    

    @Override
    public String toString() {
        return "Constraint [type=" + type + ", rhs=" + rhs + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (lhs == null) ? 0 : lhs.hashCode());
        result = prime * result + ( (rhs == null) ? 0 : rhs.hashCode());
        result = prime * result + ( (type == null) ? 0 : type.hashCode());
        return result;
    }


    public String getLhs() {
        return lhs;
    }
    
}
