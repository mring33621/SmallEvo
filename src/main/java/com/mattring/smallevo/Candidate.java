package com.mattring.smallevo;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 *
 * @author mring
 */
public class Candidate {

    public static final Supplier<Candidate> BLANK_SUPPLIER = new Supplier<Candidate>() {
        @Override
        public Candidate get() {
            return new Candidate();
        }
    };
    
    private double score;
    private double[] genome;

    public Candidate() {
    }

    public Candidate(double[] genome) {
        this.genome = genome;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double[] getGenome() {
        return genome;
    }

    public void setGenome(double[] genome) {
        this.genome = genome;
    }

    public void reset() {
        this.score = 0d;
    }

    @Override
    public String toString() {
        return Arrays.toString(genome) + "; " + score;
    }

}
