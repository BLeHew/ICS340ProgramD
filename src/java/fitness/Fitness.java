package fitness;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Fitness {
    private double[] semFitness = new double[11];
    
    public Fitness() {
        Arrays.fill(semFitness, 1000);
    }
    public Fitness(ArrayList<Integer> badSems) {
        Arrays.fill(semFitness, 1000);
        for(Integer i : badSems) {
            removeSems(i);
        }
    }
    public Fitness(String[] splitString) {
        for(int i = 1; i < splitString.length; i++) {
            semFitness[i - 1] = Double.parseDouble(splitString[i]);
        }
    }
    private void removeSems(Integer i) {
        for(int j = i; j < semFitness.length ; j+= 3) {
            semFitness[j] *= 0;
        }
    }
    public void update(int semester, double amount) {
        semFitness[semester] *= amount;
    }
    public void reset() {
        Arrays.fill(semFitness, 100000);
    }
    public int getHealthiestSemester(ArrayList<Integer> nonFullSemesters) {
       int best = nonFullSemesters.get(0);
       for(int j = 1; j < nonFullSemesters.size() ; j++) {
           best = semFitness[j] > semFitness[best] ? j : best;
       }
       
       return best;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(double d : semFitness) {
            DecimalFormat dec = new DecimalFormat("#0.00");
            sb.append(dec.format(d) + " ");
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
}
