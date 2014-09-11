
package com.mattring.smallevo.functions.evo;

import com.mattring.smallevo.Candidate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 *
 * @author mring
 */
public class MutationFn<T extends Candidate> implements UnaryOperator<T> {
    
    private final Supplier<T> blankCandidateSupplier;

    public MutationFn(Supplier<T> blankCandidateSupplier) {
        this.blankCandidateSupplier = blankCandidateSupplier;
    }

    @Override
    public T apply(T a) {
        final int genomeLen = a.getGenome().length;
        final double[] bGenome = new double[genomeLen];
        System.arraycopy(a.getGenome(), 0, bGenome, 0, genomeLen);
        final Random rng = ThreadLocalRandom.current();
        for (int i = 0; i < genomeLen; i++) {
            if (rng.nextDouble() > 0.9d) {
                // mutate gene value
                bGenome[i] = rng.nextDouble();
            }
        }
        final T b = blankCandidateSupplier.get();
        b.setGenome(bGenome);
        return b;
    }
    
}
