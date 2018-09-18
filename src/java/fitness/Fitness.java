package fitness;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class Fitness {
    private double[] semFitness = new double[11];
    
    public Fitness() {
        Arrays.fill(semFitness, 100000);
    }
    public void update(int semester, int amount) {
        semFitness[semester] *= .9999;
    }
    public void reset() {
        Arrays.fill(semFitness, 100000);
    }
    public int getHealthiestSemester() {

        Random r = new Random();
        int next = r.nextInt(11);
        if(semFitness[next] > 50000) {
            return next;
        }
        return r.nextInt(11);
        
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
}
