package schedule;

import java.util.ArrayList;

public class CourseSchedule {
    private final String fallDays;   //days offered in the fall
    private final String springDays; //days offered in the spring
    private final String summerDays; //days offered in the summer
    private final ArrayList<Integer> badSems;

    public CourseSchedule(String fallDays, String springDays,String summerDays) {
        this.fallDays = fallDays;
        this.springDays = springDays;
        this.summerDays = summerDays;
        badSems = new ArrayList<Integer>();
        if(fallDays.endsWith("-")) {
            badSems.add(0);
        }
        if(springDays.endsWith("-")) {
            badSems.add(1);
        }
        if(summerDays.endsWith("-")) {
            badSems.add(2);
        }
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
    public ArrayList<Integer> getDashes(){
        return badSems;
    }
    public Character charAt(int semTaken, int index) {
        return getDays(semTaken).charAt(index);
    }
    public Integer length(int semTaken) {
        return getDays(semTaken).length();
    }
    public boolean hasOnline(int semTaken) {
        return getDays(semTaken).endsWith("O");
    }

    public Character getDayFromSchedule(int semTaken, int index) {
        return getDays(semTaken).charAt(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(fallDays + "\t");
        sb.append(springDays + "\t");
        sb.append(summerDays + "\t");
        return sb.toString();
    }
}