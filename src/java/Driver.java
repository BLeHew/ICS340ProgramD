public class Driver {
    public static void main(String args[]) {

        FileProcessor fp = new FileProcessor();
        FileProcessor fp1 = new FileProcessor();

        //Courses cs = new Courses(fp.getCourses());

        Schedule test = new Schedule(fp.getCourses(),1);
        Schedule s  = new Schedule(fp1.getCourses());


        test.print();
        System.out.println();
        s.print();



    }
}
