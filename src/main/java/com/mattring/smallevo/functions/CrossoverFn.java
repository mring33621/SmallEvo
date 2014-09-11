
package com.mattring.smallevo.functions;

import com.mattring.smallevo.Candidate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * The 'mating' function
 * @author mring
 */
public class CrossoverFn<T extends Candidate> implements BiFunction<T, T, List<T>>{
    
    private final Supplier<T> blankCandidateSupplier;

    public CrossoverFn(Supplier<T> blankCandidateSupplier) {
        this.blankCandidateSupplier = blankCandidateSupplier;
    }

    /**
     * Mates 2 candidates and returns 2 children.
     * Each gene has a 50/50 chance of swapping from mom/dad.
     * @param mom
     * @param dad
     * @return list of 2 children
     */
    @Override
    public List<T> apply(T mom, T dad) {
        final int genomeLen = mom.getGenome().length;
        final double[] childAGenome = new double[genomeLen];
        final double[] childBGenome = new double[genomeLen];
        System.arraycopy(mom.getGenome(), 0, childAGenome, 0, genomeLen);
        System.arraycopy(dad.getGenome(), 0, childBGenome, 0, genomeLen);
        final Random rng = ThreadLocalRandom.current();
        for (int i = 0; i < genomeLen; i++) {
            if (rng.nextBoolean()) {
                // swap gene value
                double tmp = childAGenome[i];
                childAGenome[i] = childBGenome[i];
                childBGenome[i] = tmp;
            }
        }
        T childA = blankCandidateSupplier.get();
        childA.setGenome(childAGenome);
        T childB = blankCandidateSupplier.get();
        childB.setGenome(childBGenome);
        return Arrays.asList(childA, childB);
    }
    
}
