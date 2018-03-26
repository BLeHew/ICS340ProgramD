import java.io.*;
import java.util.*;

public class FileProcessor {
    private File constraintsFile = new File("constraints.txt");
    private File coursesFile = new File("course.txt");

    private CourseList courses = new CourseList();
    private Scanner sc;

    public FileProcessor() {
        createCourseList();
        addConstraintsToCourses();
        //courses.print();
    }
    public CourseList getCourses() {
        return courses;
    }
    private void addConstraintsToCourses() {
        try {
            sc = new Scanner(constraintsFile);
            sc.nextLine();

            while (sc.hasNextLine()) {

                String[] splitString = sc.nextLine().split("\\s+");

                courses.addConstraint(splitString[0],splitString[1],splitString[2]);

            }
        } catch ( FileNotFoundException x ) {
            x.printStackTrace();
        }

    };

    private void createCourseList() {

        try {
            sc = new Scanner(coursesFile);
            sc.nextLine();

            while (sc.hasNextLine()) {

                String[] splitString = sc.nextLine().split("\\s+");


                courses.put((new Course(splitString[0], splitString[1], splitString[2], splitString[3])));
            }
        } catch ( FileNotFoundException x ) {
            x.printStackTrace();
        }

    };



}
