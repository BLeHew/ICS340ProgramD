package fitness;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

public class CoursesFitness{
    private HashMap<String,Fitness> courseFitMap = new HashMap<String,Fitness>();
    
    private Scanner fileScanner;
    
    public CoursesFitness(File fitnessFile) {
        if(createFitnessFile(fitnessFile)) {
            while(fileScanner.hasNextLine()) {
                String[] splitString = fileScanner.nextLine().split("\\s+");
                
                courseFitMap.put(splitString[0], new Fitness(splitString));
            }
        }
        
    }
    public CoursesFitness() {
        
    }
    public boolean createFitnessFile(File fitnessFile) {
        try {
            fileScanner = new Scanner(fitnessFile);
            return true;
        } catch ( FileNotFoundException x ) {
            x.printStackTrace();
            return false;
        }
    }
    public void add(String course,ArrayList<Integer> badSems) {
        if(!courseFitMap.containsKey(course)) {
            courseFitMap.put(course, new Fitness(badSems));
        }
        
    }
    public void printCourseStats() {
        System.out.println("Best Semesters for each course: " );
        for(Entry<String,Fitness> e : courseFitMap.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue().getHealthiestSemester());
        }
    }
    public int getHealthiestSemester(String course,ArrayList<Integer> nonFullSemesters) {
        return courseFitMap.get(course).getHealthiestSemester(nonFullSemesters);
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
