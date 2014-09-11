package com.mattring.smallevo.functions;

import com.mattring.smallevo.Candidate;
import com.mattring.smallevo.EvoContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Does the following:
 * Takes the 10 best candidates (by score ranking) from the previous generation.
 * Then mates them 2 by 2.
 * Then mutates their offspring.
 * Then fills in the next generation with random candidates.
 * @author mring
 */
public class PrepareNextGenerationFn<T extends Candidate> implements UnaryOperator<List<T>> {

    private final int generationSize;
    private final RandomPopulationFn<T> rndPopFn;
    private final CrossoverFn<T> xoverFn;
    private final MutationFn<T> mutFn;
    private final Comparator<T> scoreComparator;

    public PrepareNextGenerationFn(EvoContext ctx, Supplier<T> blankCandidateSupplier) {
        this.generationSize = ctx.getGenerationSz();
        final RandomCandidateSupplier<T> rndCandSupplier
                = new RandomCandidateSupplier<>(blankCandidateSupplier, ctx.getGenomeSz());
        this.rndPopFn = new RandomPopulationFn<>(rndCandSupplier);
        this.xoverFn = new CrossoverFn<>(blankCandidateSupplier);
        this.mutFn = new MutationFn<>(blankCandidateSupplier);
        this.scoreComparator = ctx.getScoreComparator().reversed();
    }

    @Override
    public List<T> apply(List<T> prevGen) {
        final List<T> nextGen = new ArrayList<>(rndPopFn.apply(generationSize));
        if (prevGen != null && !prevGen.isEmpty()) {
            final List<T> prevGenCopy = new ArrayList<>(prevGen);
            Collections.sort(prevGenCopy, scoreComparator);
            // top 10 are survivors
            final List<T> survivors = prevGenCopy.subList(0, 10);
            final List<T> children
                    = IntStream.range(1, survivors.size())
                    .mapToObj(i -> xoverFn.apply(survivors.get(i - 1), survivors.get(i)))
                    .flatMap(chpair -> chpair.stream())
                    .map(mutFn)
                    .collect(Collectors.toList());
            nextGen.addAll(children);
        }
        return nextGen;
    }

}
