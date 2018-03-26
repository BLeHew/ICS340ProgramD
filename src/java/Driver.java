public class Driver {
    public static void main(String args[]) {

        FileProcessor fp = new FileProcessor();

        Schedule s = new Schedule(fp.getCourses());

        s.print();

        /*
        Course c = new Course("ICS471",null,null,null);
        Course c1 = new Course("ICS462",null,null,null);
        Course c2 = new Course("ICS440",null,null,null);
        Course c3 = new Course("ICS365",null,null,null);
        Course c4 = new Course("ICS499",null,null,null);
        Course c5 = new Course("ICS311",null,null,null);
        Course c6 = new Course("ICS460",null,null,null);
        Course c7 = new Course("Math310",null,null,null);


        s.changeCourseSemester(c, 10);
        s.changeCourseSemester(c1, 9);
        s.changeCourseSemester(c2, 8);
        s.changeCourseSemester(c3, 7);
        s.changeCourseSemester(c4, 10);
        s.changeCourseSemester(c5, 5);
        s.changeCourseSemester(c6, 10);
        s.changeCourseSemester(c7, 3);
        s.changeCourseSemester("ICS240",8);
        */
        int numConflicts;
        while(s.getNumConflicts() > 0) {
            for(Course c: s.getCourses().values()) {
                numConflicts = c.getNumConflicts();

                for(int i = 0; i < 11; i++) {
                    s.changeCourseSemester(c, i);

                }
            }

        }

        /*
        for(Course c: fp.getCourses().values()) {
            s.changeCourseSemester(c, r.nextInt(11));
        }
        */


    }
}
