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
    public void update(int semester, double amount) {
        semFitness[semester] *= amount;
    }
    public void reset() {
        Arrays.fill(semFitness, 100000);
    }
    public int getHealthiestSemester(ArrayList<Integer> nonFullSemesters) {
       int best = 0;
       for(Integer i : nonFullSemesters) {
           best = semFitness[i] > semFitness[best] ? i : best;
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
