
package com.mattring.smallevo.functions;

import com.mattring.smallevo.Candidate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * Supplies candidates with random gene values in range (0.0d, 1.0d)
 * @author mring
 */
public class RandomCandidateSupplier<T extends Candidate> implements Supplier<T> {
    
    private final Supplier<T> blankCandidateSupplier;
    private final int genomeLength;

    public RandomCandidateSupplier(Supplier<T> blankCandidateSupplier, int genomeLength) {
        this.blankCandidateSupplier = blankCandidateSupplier;
        this.genomeLength = genomeLength;
    }

    @Override
    public T get() {
        final Random rng = ThreadLocalRandom.current();
        final T candidate = blankCandidateSupplier.get();
        candidate.setGenome(
                rng.doubles().limit(genomeLength).toArray());
        return candidate;
    }
    
}
