package fitness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class CoursesFitness{
    private HashMap<String,Fitness> courseFitMap = new HashMap<String,Fitness>();
    public void add(String course) {
        courseFitMap.put(course, new Fitness());
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
