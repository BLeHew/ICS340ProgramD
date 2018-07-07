public class Semester {
    //private String daysTaken;
    private int numDaysTaken;
    private int semNum;
    private Courses courses;

    public Semester(int semNum) {
        courses = new Courses();
        this.semNum = semNum;
        numDaysTaken = 0;
        //daysTaken = new String("");
    }

    public void add(Course c) {
        courses.put(c);
        //daysTaken.concat(String.valueOf(c.getDayTaken()));
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
    /*
    public void assignDaysToCourses() {
        ArrayList<Character> daysTaken = new ArrayList<>();
        for(Course c: courses.values()) {
            if(c.getSchedule().endsWith("O")) {
                c.setDay('O');
            }
            else
            {
                for(int i = 0; i < c.getSchedule().length();i++) {
                    if(!daysTaken.contains(c.getSchedule().charAt(i))) {
                        c.setDay(c.getSchedule().charAt(i));
                        daysTaken.add(c.getSchedule().charAt(i));
                    }
                }
            }
        }
    }
    */
    public void assignDaysToCourses() {
        StringBuffer daysTaken = new StringBuffer();
        for(Course c: courses.values()) {
            if(c.getSchedule().endsWith("O")) {
                c.setDay('O');
            }
            else
            {
                if(c.setNextAvailableDay(daysTaken.toString())){
                    daysTaken.append(c.getDayTaken());
                }
            }

        }
    }

}
