import java.io.*;
import java.util.*;

import constraints.CourseConstraints;
import course.*;
import fitness.CoursesFitness;
import fitness.Fitness;
import schedule.CourseSchedule;
import schedule.CourseSchedules;

public class FileProcessor {
    private File constraintsFile = new File("constraints.txt");
    private File coursesFile = new File("course.txt");

    private HashMap<String,Course> courses = new HashMap<String,Course>();

    private Scanner sc;

    public FileProcessor() {

        createCoursesFileScanner();
        createCourseList();
        createConstraintsFileScanner();
        addConstraintsToCourses();

    }

    public HashMap<String,Course> getCourses() {
        return courses;
    }
    private void addConstraintsToCourses() {

        while (sc.hasNextLine()) {

            String[] splitString = sc.nextLine().split("\\s+");
            CourseConstraints.getInstance().addConstraint(splitString[0],splitString[1],splitString[2]);
            if(splitString[1].equals("<")) {
                CoursesFitness.getInstance().updateFitness(splitString[0], 10, Courses.DOMAINCONFLICT);
                CoursesFitness.getInstance().updateFitness(splitString[2], 0, Courses.DOMAINCONFLICT);
             }
           }
    }

    private void createCourseList() {
        sc.nextLine();
        while (sc.hasNextLine()) {

            String[] splitString = sc.nextLine().split("\\s+");
            courses.put(splitString[0], new Course());
            CourseSchedules.getInstance().put(splitString[0], new CourseSchedule(splitString[1], splitString[2], splitString[3]));
            CoursesFitness.getInstance().add(splitString[0],new Fitness());
            
            if(CourseSchedules.getInstance().get(splitString[0]).getDashes().size() > 0) {
                CoursesFitness.getInstance().trimBadSems(splitString[0], CourseSchedules.getInstance().get(splitString[0]).getDashes());
            }
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
