package fitness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import course.Course;

public class CoursesFitness{
    private HashMap<String,Fitness> courseFitMap = new HashMap<String,Fitness>();
    
    private static CoursesFitness singleton = new CoursesFitness();
    
    private CoursesFitness() {
        
    }
    public void add(String string, Fitness fitness) {
        courseFitMap.put(string, fitness);
        
    }
    public static CoursesFitness getInstance() {
        return singleton;
    }
    
    public void trimBadSems(String course,ArrayList<Integer> badSems) {
       courseFitMap.get(course).trimBadSems(badSems);
    }
    public int getHealthiestSemester(String course,ArrayList<Integer> nonFullSemesters) {
        return courseFitMap.get(course).getHealthiestSemester(nonFullSemesters);
    }
    public void updateFitness(Entry<String,Course> entry, double amount) {
        updateFitness(entry.getKey(),entry.getValue().getSemTaken(),amount);
    }
    public void updateFitness(String course,int semester, double amount) {
        courseFitMap.get(course).update(semester, amount);
    }
    public void resetFitness(String course) {
        courseFitMap.get(course).reset();
    }
    public void resetAllFitness() {
        for(Fitness f : courseFitMap.values()) {
            f.reset();
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Entry<String, Fitness> e : courseFitMap.entrySet()) {
            sb.append(e.getKey() + " " +  e.getValue() + "\n");
        }
        return sb.toString();
    }
    public int getHealthiestSemester(String c) {
        return courseFitMap.get(c).getHealthiestSemester();
    }
    

}   
