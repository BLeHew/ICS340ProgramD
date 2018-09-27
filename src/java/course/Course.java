package course;

import fitness.Fitness;
import schedule.CourseSchedule;

public class Course {

    private Character day; // day this course is currently assigned to
    private Integer semTaken;
    private Fitness fitness;
    private final CourseSchedule courseSchedule;
    private final String name;
    
    public Course(String name,String fallDays, String springDays, String summerDays) {
        this.name = name;
        courseSchedule = new CourseSchedule(fallDays,springDays,summerDays);
        fitness = new Fitness();
    }
    public Course(String name, CourseSchedule courseSchedule) {
        this.name = name;
        this.courseSchedule = courseSchedule;
        fitness = new Fitness();
    }
    public CourseSchedule getSchedule() {
        return courseSchedule;
    }
    public String getName() {
        return name;
    }
    public Fitness getFitness() {
        return fitness;
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
        return day + "\t";
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

}
