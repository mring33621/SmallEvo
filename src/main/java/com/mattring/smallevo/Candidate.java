package com.mattring.smallevo;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * The simplest candidate consists of a genome and a score
 * @author mring
 */
public class Candidate {

    /**
     * supplies candidates with null genome and 0 score
     */
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

    /**
     * resets the score to 0
     */
    public void reset() {
        this.score = 0d;
    }

    @Override
    public String toString() {
        return Arrays.toString(genome) + "; " + score;
    }

}
