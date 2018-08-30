package course;
import java.util.*;

public class Semester {
    private int numDaysTaken;
    private LinkedHashSet<Character> domain;
    private final int semNum;

    public Semester(int semNum) {
        domain = new LinkedHashSet<>();
        numDaysTaken = 0;
        this.semNum = semNum;
    }

    public void add(String course,Courses courses) {
        numDaysTaken++;

        /*
        for(int i = 0; i < c.getSchedule().length(); i++) {
            domain.add(c.getSchedule().charAt(i));
        }

        if(c.getSchedule().endsWith("O")) {
            courseList.get(c.getName()).setDay('O');
            domain.remove('O');

        }else if (domain.isEmpty()) {
            courseList.get(c.getName()).setDay('-');
        }
        else {
            for(char day : c.getSchedule().toCharArray()) {
                if(domain.contains(day)) {
                    domain.remove(day);
                    break;
                }
            }
        }
        numDaysTaken++;
        if(!isNumDaysValid()) {
            courseList.get(c.getName()).addConflict(Course.SEMCONF);
        }
        */

    }
    public void remove(String c,Courses courseList) {
        /*
        char[] temp = courseList.get(c).getSchedule().toCharArray();

        for(char ch : temp) {
            domain.remove(ch);
        }
        */
        numDaysTaken--;
    }
    public boolean isNumDaysValid() {

        if(semNum % 3 == 0) {
            if(numDaysTaken > 3) {
                return false;
            }
        }
        if((semNum + 2) % 3 == 0) {
            if(numDaysTaken > 3) {
                return false;
            }
        }
        if((semNum + 1) % 3 == 0) {
            if(numDaysTaken > 2) {
                return false;
            }
        }
        return true;
    }
}
