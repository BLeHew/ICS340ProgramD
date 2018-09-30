package course;

import fitness.Fitness;
import schedule.CourseSchedule;

import java.util.HashMap;
import java.util.HashSet;

public class Course {

    private Character day; // day this course is currently assigned to
    private Integer semTaken;
    private Fitness fitness;
    private CourseSchedule courseSchedule;
    private final String name;
    private HashMap<String,String> conflictingCourses;
    private String constraintType;
    public Course(String name,String fallDays, String springDays, String summerDays) {
        this.name = name;
        courseSchedule = new CourseSchedule(fallDays,springDays,summerDays);
        fitness = new Fitness();
        conflictingCourses = new HashMap<>();
    }
    public Course(String name, CourseSchedule courseSchedule) {
        this.name = name;
        this.courseSchedule = courseSchedule;
        fitness = new Fitness();
        fitness.trimBadSems(courseSchedule.getDashes());
        conflictingCourses = new HashMap<>();
    }
    public Course(String name,String constraintType){
        this.name = name;
        this.constraintType = constraintType;
        conflictingCourses = new HashMap<>();
    }
    public HashMap<String,String> getConflictingCourses(){
        return conflictingCourses;
    }
    public boolean hasConflictWith(Course other){
        if(conflictingCourses.containsKey(other.getName())) {
            switch (conflictingCourses.get(other.getName())) {
                case "<":
                    return semTaken >= other.semTaken;
                case ">":
                    return semTaken <= other.semTaken;
                case "<=":
                    return semTaken > other.semTaken;
                case ">=":
                    return semTaken < other.semTaken;
            }
        }
        return false;
    }
    public boolean hasConflictingCourses(){
        return !conflictingCourses.isEmpty();
    }
    public void addConflictingCourse(Course course){
        conflictingCourses.put(course.getName(),course.getConstraintType());
    }
    public String getConstraintType(){
        return constraintType;
    }
    public void setConstraintType(String constraintType){
        this.constraintType = constraintType;
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
        return name + "\t" + day + "\t";
    }

    public Character getDayTaken() {
        return day;
    }


}
