
package com.mattring.smallevo.functions;

import com.mattring.smallevo.Candidate;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Makes small random changes to a candidate's genome
 * @author mring
 */
public class MutationFn<T extends Candidate> implements UnaryOperator<T> {
    
    private final Supplier<T> blankCandidateSupplier;

    public MutationFn(Supplier<T> blankCandidateSupplier) {
        this.blankCandidateSupplier = blankCandidateSupplier;
    }

    /**
     * Each gene has a 10% chance of being replaced with a new random value.
     * @param candidateA candidate
     * @return a slightly modified copy of the original candidate
     */
    @Override
    public T apply(T candidateA) {
        final int genomeLen = candidateA.getGenome().length;
        final double[] copiedGenome = Arrays.copyOf(candidateA.getGenome(), genomeLen);
        final Random rng = ThreadLocalRandom.current();
        for (int i = 0; i < genomeLen; i++) {
            if (rng.nextDouble() > 0.9d) {
                // mutate gene value
                copiedGenome[i] = rng.nextDouble();
            }
        }
        final T candidateB = blankCandidateSupplier.get();
        candidateB.setGenome(copiedGenome);
        return candidateB;
    }
    
}
