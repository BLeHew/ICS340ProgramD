package schedule;

import java.util.ArrayList;
import java.util.Random;

import course.Courses;

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
    public ArrayList<Integer> schedsWithDash(){
        ArrayList<Integer> retArr = new ArrayList<Integer>();
        if(fallDays.endsWith("-")) {
            retArr.add(0);
        }
        if(springDays.endsWith("-")) {
            retArr.add(1);
        }
        if(summerDays.endsWith("-")) {
            retArr.add(2);
        }
        return retArr.size() > 0 ? retArr : null;
    }
    public String getFallDays() {
        return fallDays;
    }

    public String getSpringDays() {
        return springDays;
    }
    public Character getDayFromSchedule(int semTaken, int index) {
        return getDays(semTaken).charAt(index);
    }

    public String getSummerDays() {
        return summerDays;
    }
    public Character getRandomDayFromSchedule(int semTaken) {
        Random r = new Random();
        return getDays(semTaken).charAt(r.nextInt(getDays(semTaken).length() + 1));
    }
}
