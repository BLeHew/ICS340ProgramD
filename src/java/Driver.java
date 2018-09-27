import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;

import course.Course;
import course.Courses;
import fitness.CoursesFitness;
import schedule.CourseSchedule;

public class Driver {
    public static void main(String args[]) {
        
        FileProcessor fp1 = new FileProcessor();
        
        System.out.println();
        
        Courses s = new Courses(fp1.getCourses()); 
        
        s.assignSemesters();
        s.checkConflicts();
        long msStart = System.currentTimeMillis();
        s.solve();
        long msEnd = System.currentTimeMillis() - msStart;
        System.out.println(s);
        System.out.println(msEnd);
        
        
      
        long best = Long.MAX_VALUE;
        long worst = Long.MIN_VALUE;
        long average = 0;
        /*
        for(int i = 1; i < 100; i++) {
            s.assignSemesters();
            s.checkConflicts();
            long msStart = System.currentTimeMillis();
            s.solve();
            long msEnd = System.currentTimeMillis() - msStart;
            
            if(msEnd < best && msEnd > 0) {
                best = msEnd;
            }
            if(msEnd > worst) {
                worst = msEnd;
            }
            average = average * (i - 1)/i + msEnd / i;
            System.out.println(i + " Current: " + msEnd + "\tBest: " + best + "\tWorst: " + worst + "\tAverage: " + average);
        }
        
        */
              
        
    }
}
