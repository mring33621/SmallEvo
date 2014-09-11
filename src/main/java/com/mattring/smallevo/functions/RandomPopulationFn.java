package com.mattring.smallevo.functions;

import com.mattring.smallevo.Candidate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Provides a specified size list of random candidates 
 * @author mring
 */
public class RandomPopulationFn<T extends Candidate> implements Function<Integer, List<T>> {

    private final RandomCandidateSupplier<T> supplier;

    public RandomPopulationFn(RandomCandidateSupplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public List<T> apply(Integer popSize) {
        return IntStream.range(0, popSize)
                .parallel()
                .mapToObj(i -> supplier.get()).collect(Collectors.toList());
    }

}
