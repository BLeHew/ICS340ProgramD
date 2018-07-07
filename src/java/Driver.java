public class Driver {
    public static void main(String args[]) {

        FileProcessor fp = new FileProcessor();

        Schedule s = new Schedule(fp.getCourses(),1);

        s.print();



    }
}
