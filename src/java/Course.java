import java.util.*;

public class Course {
    private char day; // day this course is currently assigned to
    private String name;

    private String fallDays;   //days offered in the fall
    private String springDays; //days offered in the spring
    private String summerDays; //days offered in the summer


    private ArrayList<String> prereqs = new ArrayList<>(); //courses needed before this course
    private ArrayList<String> coreqs = new ArrayList<>(); //courses that require this course

    public Course(String name,
                  String fallDays,
                  String springDays,
                  String summerDays) {
        this.name = name;
        this.fallDays = fallDays;
        this.springDays = springDays;
        this.summerDays = summerDays;

    }

    public Course() {};


    public String getSchedule(char term) {

        switch(term) {
            case 's': return springDays;
            case 'm': return summerDays;
            case 'f': return fallDays;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getFallDays() {
        return fallDays;
    }

    public String getSpringDays() {
        return springDays;
    }

    public String getSummerDays() {
        return summerDays;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (name == null) ? 0 : name.hashCode());
        return result;
    }

    public boolean equals(String other) {
        if(this.name.equals(other)) {
            return true;
        }
        return false;
    }


}
