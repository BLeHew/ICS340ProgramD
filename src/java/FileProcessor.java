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
                courses.get(splitString[0]).getFitness().update(10, Courses.DOMAINCONFLICT);
                courses.get(splitString[2]).getFitness().update(0, Courses.DOMAINCONFLICT);
             }
           }
    }

    private void createCourseList() {
        sc.nextLine();
        while (sc.hasNextLine()) {

            String[] splitString = sc.nextLine().split("\\s+");
            CourseSchedule courseSchedule = new CourseSchedule(splitString[1],splitString[2],splitString[3]);
            
            courses.put(splitString[0],new Course(splitString[0],courseSchedule));
            
            if(!courseSchedule.getDashes().isEmpty()) {
                courses.get(splitString[0]).getFitness().trimBadSems(courseSchedule.getDashes());
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
