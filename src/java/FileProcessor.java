import java.io.*;
import java.util.*;

public class FileProcessor {
    private File constraintsFile = new File("constraints.txt");
    private File coursesFile = new File("course.txt");

    private Courses courses = new Courses();
    private Scanner sc;

    public FileProcessor() {
        createCoursesFileScanner();
        createCourseList();
        createConstraintsFileScanner();
        addConstraintsToCourses();
    }

    public Courses getCourses() {
        return courses;
    }
    private void addConstraintsToCourses() {
        sc.nextLine();

        while (sc.hasNextLine()) {

            String[] splitString = sc.nextLine().split("\\s+");

            courses.addConstraint(splitString[0],splitString[1],splitString[2]);
            }
    }

    private void createCourseList() {
        sc.nextLine();
        while (sc.hasNextLine()) {

            String[] splitString = sc.nextLine().split("\\s+");

            courses.put((new Course(splitString[0], splitString[1], splitString[2], splitString[3])));
        }

    }
    private void createCoursesFileScanner() {
        try {
            sc = new Scanner(coursesFile);
        } catch ( FileNotFoundException x ) {
            x.printStackTrace();
        }
    }
    private void createConstraintsFileScanner() {
        try {
            sc = new Scanner(constraintsFile);
        } catch ( FileNotFoundException x ) {
            x.printStackTrace();
        }
    }


}
