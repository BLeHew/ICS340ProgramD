import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;

import course.Course;
import course.Courses;
import schedule.CourseSchedule;

public class Driver {
    public static void main(String args[]) {
        
        FileProcessor fp1 = new FileProcessor();


        System.out.println();
        Schedule s  = new Schedule(fp1.getCourses());
                    
        s.assignSemesters();
        s.checkConflicts();
        //s.print();
        
        
        
        System.out.println();

        s.solve();
        
        System.out.println();      
        
    }
}
