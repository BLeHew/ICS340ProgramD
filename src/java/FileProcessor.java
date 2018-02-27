import java.io.*;
import java.util.*;

public class FileProcessor {
    private File constraintsFile = new File("constraints.txt");
    private File coursesFile = new File("course.txt");

    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Constraint> constraints = new ArrayList<>();

    private Scanner sc;

    public FileProcessor() {
        createCourseList();
        createConstraintList();
    }

    private void createConstraintList() {
        try {
            sc = new Scanner(constraintsFile);
            sc.nextLine();

            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();

                String[] splitString = nextLine.split("\\s+");

                constraints.add(new Constraint(courses.get(courses.indexOf(splitString[0])), courses.get(courses.indexOf(splitString[2])), splitString[1].charAt(0)));
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
                String nextLine = sc.nextLine();

                String[] splitString = nextLine.split("\\s+");

                courses.add(new Course(splitString[0], splitString[1], splitString[2], splitString[3]));
            }
        } catch ( FileNotFoundException x ) {
            x.printStackTrace();
        }

    };



}
