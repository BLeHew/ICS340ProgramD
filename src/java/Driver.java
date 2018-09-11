import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import course.Course;
import course.Courses;
import schedule.CourseSchedule;

public class Driver {
    public static void main(String args[]) {
        
        FileProcessor fp = new FileProcessor();
        FileProcessor fp1 = new FileProcessor();

        //Courses cs = new Courses(fp.getCourses());

        Schedule test = new Schedule(fp.getCourses(),1);
        
        Schedule s  = new Schedule(fp1.getCourses());
                    
        s.assignSemesters();
        
        System.out.println();
        s.checkConflicts();
        s.print();

        
        for(Course c : test.getCourses().values()) {
            s.setSemTaken(c.getName(), c.getSemTaken());
        }
        
        
        //test.checkConflicts();
        //s.checkConflicts();
        
        //test.print();
        System.out.println();
        s.print();
    
        
    }
}
