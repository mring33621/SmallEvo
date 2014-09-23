package com.mattring.smallevo.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/**
 * Uses a double array to deterministically shuffle a list.
 * @author mring
 * @param <T>
 */
public class RepeatableShuffler<T> implements BiFunction<List<T>, double[], List<T>> {

    @Override
    public List<T> apply(List<T> incomingList, double[] moves) {
        final int len = incomingList.size();
        final Map<Double, T> sortedMap = new TreeMap<>();
        IntStream.range(0, len).forEach(i -> {
            T item = incomingList.get(i);
            Double move = moves[i];
            sortedMap.put(move, item);
        });
        List<T> shuffledList = new ArrayList<>(sortedMap.values());
        return shuffledList;
    }

}
