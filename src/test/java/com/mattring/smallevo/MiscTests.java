/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mattring.smallevo;

import com.mattring.smallevo.functions.CrossoverFn;
import com.mattring.smallevo.functions.MutationFn;
import com.mattring.smallevo.functions.PrepareNextGenerationFn;
import com.mattring.smallevo.functions.RandomCandidateSupplier;
import com.mattring.smallevo.functions.RandomPopulationFn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import org.junit.Test;

/**
 *
 * @author mring
 */
public class MiscTests {
    
    @Test
    public void testRandomPopulationFn() {
        System.out.println("testRandomPopulationFn");
        EvoContext<Candidate> ctx = new EvoContext<>(5, 3, true);
        List<Candidate> cands 
                = new RandomPopulationFn(
                        new RandomCandidateSupplier<>(Candidate.BLANK_SUPPLIER, ctx.getGenomeSz()))
                        .apply(ctx.getGenerationSz());
        cands.forEach(c -> 
                System.out.println(c));
    }
    
    @Test
    public void testPairUp() {
        System.out.println("testPairUp");
        List<String> l = Arrays.asList("a", "b", "c", "d", "e");
        IntStream.range(1, l.size())
                .mapToObj(i -> Arrays.asList(l.get(i-1), l.get(i)))
                .forEach(System.out::println);
    }
    
    @Test
    public void testCrossoverFn() {
        System.out.println("testCrossoverFn");
        Candidate a = new Candidate(new double[] {1d,2d,3d,4d});
        Candidate b = new Candidate(new double[] {5d,6d,7d,8d});
        for (int i = 0; i < 10; i++) {
            List<Candidate> children = new CrossoverFn<>(Candidate.BLANK_SUPPLIER).apply(a, b);
            children.forEach(c -> 
                System.out.println(c));
        }
    }
    
    @Test
    public void testMutationFn() {
        System.out.println("testMutationFn");
        Candidate a = new Candidate(new double[] {0d,0d,0d,0d});
        for (int i = 0; i < 10; i++) {
            Candidate m = new MutationFn<>(Candidate.BLANK_SUPPLIER).apply(a);
            System.out.println(m);
        }
    }
    
    @Test
    public void testPrepareNextGenerationFn() {
        System.out.println("testPrepareNextGenerationFn");
        List<Candidate> pop = new ArrayList<>();
        System.out.println("  gen 0");
        for (double d = 0d; d < 11d; d++) {
            Candidate c = new Candidate(new double[] {d,d,d});
            c.setScore(d);
            pop.add(c);
            System.out.println(c);
        }
        EvoContext ctx = new EvoContext(10, 3, true);
        for (int i = 1; i < 11; i++) {
            System.out.println("  gen " + i);
            List<Candidate> newPop = new PrepareNextGenerationFn<>(ctx, Candidate.BLANK_SUPPLIER).apply(pop);
            newPop.forEach(c -> 
                System.out.println(c));
        }
    }
    
}
