import java.util.*;

public class Semester {
    //private String daysTaken;
    private int numDaysTaken;
    private int semNum;
    private Courses courses;

    public Semester(int semNum) {
        courses = new Courses();
        this.semNum = semNum;
        numDaysTaken = 0;
    }

    public void add(Course c) {
        courses.put(c);
        numDaysTaken++;
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
    public void assignDaysToCourses() {
        String domain = createDayDomain();

        int i = 0;

        for(Course c: courses.values()) {


            if(c.getSchedule().endsWith("O")) {
                c.setDay('O');
            }
            else {
                if(i > domain.length()) {
                    c.setDay('-');
                }
                else {
                    c.setDay(domain.charAt(i));
                    i++;
                }
            }

        }
    }
    public String createDayDomain() {

        LinkedHashSet<Character> lhs = new LinkedHashSet<>();
        StringBuilder sb = new StringBuilder();

        for(Course c :  courses.values()) {
            sb.append(c.getSchedule());
        }

        for(int i = 0; i < sb.toString().length(); i++) {
            lhs.add(sb.toString().charAt(i));
        }

        StringBuilder sb2 = new StringBuilder();
        for (Character ch : lhs) {
            sb2.append(ch);
        }
        return sb2.toString();
    }
}
