package fitness;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Fitness {
    private double[] semFitness = new double[11];
    private static final double MAXFITNESS = Math.pow(30, 11);
    
    public Fitness() {
        Arrays.fill(semFitness, MAXFITNESS);
    }
    void removeSems(Integer i) {
        for(int j = i; j < semFitness.length ; j+= 3) {
            semFitness[j] *= 0;
        }
    }
    public void update(int semester, double amount) {
        semFitness[semester] = (semFitness[semester] * amount);
    }
    public void reset() {
        Arrays.fill(semFitness, MAXFITNESS);
    }
    public int getHealthiestSemester(ArrayList<Integer> nonFullSemesters) {
       if(!nonFullSemesters.isEmpty()) {
           int best = nonFullSemesters.get(0);
           for(int j = 1; j < nonFullSemesters.size() ; j++) {
               best = semFitness[j] > semFitness[best] ? j : best;
           }
           return best;
       }else {
           return getHealthiestSemester();
       }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DecimalFormat dec = new DecimalFormat("#0.00");
        for(double d : semFitness) {
            sb.append(dec.format(d/MAXFITNESS) + " ");
        }
        return sb.toString();
    }
    public int getHealthiestSemester() {
        int maxAt = 0;

        for (int i = 0; i < semFitness.length; i++) {
            maxAt = semFitness[i] > semFitness[maxAt] ? i : maxAt;
        }
        return maxAt;
    }
    public int getRandomHealthySemester(){
        Random r = new Random();
        int i = r.nextInt(11);
        while(semFitness[i] == 0){
            i = r.nextInt(11);
        }
        return i;
    }
    public void trimBadSems(ArrayList<Integer> badSems) {
        for(Integer i : badSems) {
            removeSems(i);
         }
        
    }
}
