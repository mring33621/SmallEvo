package com.mattring.smallevo.example;

import com.mattring.smallevo.Candidate;
import com.mattring.smallevo.EvoContext;
import com.mattring.smallevo.functions.evo.PrepareNextGenerationFn;
import java.util.List;

/**
 *
 * @author mring
 */
public class WordsMain {

    public static void main(String[] args) {

        final char[] targetWord = args[0].toCharArray();
        final EvoContext<Candidate> ctx = new EvoContext<>(100, targetWord.length, true);
        final BestScore<Candidate> bestScoreHolder = new BestScore<>(ctx.getScoreComparator(), new Candidate());
        final PrepareNextGenerationFn<Candidate> nextGenFn = new PrepareNextGenerationFn<>(ctx, Candidate.BLANK_SUPPLIER);
        List<Candidate> pop = nextGenFn.apply(null);
        final PickPrintableChar ppcFn = new PickPrintableChar();
        final double perfectScore = targetWord.length;
        int genCnt = 1;
        while (true) {
            pop.parallelStream().forEach(cand -> {
                final char[] word = new char[targetWord.length];
                final double[] gen = cand.getGenome();
                double score = 0d;
                for (int i = 0; i < targetWord.length; i++) {
                    char letter = ppcFn.pick(gen[i]);
                    word[i] = letter;
                    if (letter == targetWord[i]) {
                        score += 1d;
                    }
                }
                cand.setScore(score);
                if (bestScoreHolder.addIfBest(cand)) {
                    System.out.println(String.valueOf(word) + " " + score);
                }
            });

            final Candidate bestCandThisGen = bestScoreHolder.getBest();
            if (bestCandThisGen.getScore() >= perfectScore) {
                break;
            }
            pop = nextGenFn.apply(pop);
            if (bestCandThisGen.getScore() > 0d) {
                pop.add(bestCandThisGen);
            }
            genCnt++;
        }

    }
}
