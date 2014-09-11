
package com.mattring.smallevo.functions.evo;

import com.mattring.smallevo.Candidate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 *
 * @author mring
 */
public class CrossoverFn<T extends Candidate> implements BiFunction<T, T, List<T>>{
    
    private final Supplier<T> blankCandidateSupplier;

    public CrossoverFn(Supplier<T> blankCandidateSupplier) {
        this.blankCandidateSupplier = blankCandidateSupplier;
    }

    @Override
    public List<T> apply(T a, T b) {
        final int genomeLen = a.getGenome().length;
        final double[] cGenome = new double[genomeLen];
        final double[] dGenome = new double[genomeLen];
        System.arraycopy(a.getGenome(), 0, cGenome, 0, genomeLen);
        System.arraycopy(b.getGenome(), 0, dGenome, 0, genomeLen);
        final Random rng = ThreadLocalRandom.current();
        for (int i = 0; i < genomeLen; i++) {
            if (rng.nextBoolean()) {
                // swap gene value
                double tmp = cGenome[i];
                cGenome[i] = dGenome[i];
                dGenome[i] = tmp;
            }
        }
        T c = blankCandidateSupplier.get();
        c.setGenome(cGenome);
        T d = blankCandidateSupplier.get();
        d.setGenome(dGenome);
        return Arrays.asList(c, d);
    }
    
}
