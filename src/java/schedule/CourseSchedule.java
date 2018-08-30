package schedule;

public class CourseSchedule {
    private final String fallDays;   //days offered in the fall
    private final String springDays; //days offered in the spring
    private final String summerDays; //days offered in the summer

    public CourseSchedule(String fallDays, String springDays,String summerDays) {
        this.fallDays = fallDays;
        this.springDays = springDays;
        this.summerDays = summerDays;
    }
    public String getDays(int semTaken) {
        if(semTaken % 3 == 0) {
            return fallDays;
        }
        if((semTaken + 2) % 3 == 0) {
            return springDays;
        }
        if((semTaken + 1) % 3 == 0) {
            return summerDays;
        }

        return null;
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
}
