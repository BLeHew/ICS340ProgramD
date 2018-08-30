package course;

public class Conflict {
    // 1 is semester conflict
    // 2 is a course conflict
    // 3 is a day conflict
    private final int type;
    private String course;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (course == null) ? 0 : course.hashCode());
        result = prime * result + type;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if(course.equals(null) || ((Conflict)obj).course.equals(null)) {
            return type == ((Conflict)obj).type;
        }else
            return course.equals(((Conflict)obj).course) && type == ((Conflict)obj).type;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public int getType() {
        return type;
    }
    public Conflict(int type) {
        this.type = type;
    }
    public Conflict(String course) {
        type = 2;
        this.course = course;
    }
}
