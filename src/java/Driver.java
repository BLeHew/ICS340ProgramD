import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;

import course.Course;
import course.Courses;
import schedule.CourseSchedule;

public class Driver {
    public static void main(String args[]) {
        
        FileProcessor fp = new FileProcessor();
        FileProcessor fp1 = new FileProcessor();

        //Courses cs = fp.getCourses();
        


        Schedule test = new Schedule(fp.getCourses(),1);
        test.checkConflicts();
        test.print();
        System.out.println();
        Schedule s  = new Schedule(fp1.getCourses());
                    
        s.assignSemesters();
        s.checkConflicts();
        s.print();
        
        
        
        System.out.println();
        /*
        for(Entry<String, Course> c : test.getCourses().entrySet()) {
            s.setSemTaken(c.getKey(), c.getValue().getSemTaken());
        }*/
        s.printCoursesFitness();
        s.solve();
        
        System.out.println();
        
        
        //System.out.println();
        
        //s.checkConflicts();

        
       
        
        
    }
}
