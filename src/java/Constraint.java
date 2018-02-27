
public class Constraint {
    private Course lhs;
    private Course rhs;
    private char type;

    public Constraint(Course lhs, Course rhs, char type) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.type = type;
    }
    public Course getLhs() {
        return lhs;
    }
    public void setLhs(Course lhs) {
        this.lhs = lhs;
    }
    public Course getRhs() {
        return rhs;
    }
    public void setRhs(Course rhs) {
        this.rhs = rhs;
    }
    public char getType() {
        return type;
    }
    public void setType(char type) {
        this.type = type;
    }


}
