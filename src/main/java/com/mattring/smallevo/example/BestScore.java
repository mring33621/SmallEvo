package com.mattring.smallevo.example;

import com.mattring.smallevo.Candidate;
import java.util.Comparator;

/**
 *
 * @author mring
 */
public class BestScore<T extends Candidate> {

    private T bestCandidate;
    private final Comparator<T> comparator;

    public BestScore(Comparator<T> comparator, T initialCandidate) {
        this.comparator = comparator;
        this.bestCandidate = initialCandidate;
    }

    public synchronized boolean addIfBest(T cand) {
        if (comparator.compare(cand, bestCandidate) > 0) {
            bestCandidate = cand;
            return true;
        }
        return false;
    }

    public synchronized T getBest() {
        return bestCandidate;
    }

}
