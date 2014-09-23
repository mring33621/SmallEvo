package com.mattring.smallevo.examples.phrases;

import com.mattring.smallevo.Candidate;
import com.mattring.smallevo.EvoContext;
import com.mattring.smallevo.examples.BestScore;
import com.mattring.smallevo.functions.PrepareNextGenerationFn;
import java.util.List;

/**
 * Attempts to evolve a genome to match the incoming phrase (at cmd line
 * args[0]) The incoming phrase should be quoted, if longer than one word.
 * Intended as a simple example of using SmallEvo.
 *
 * @author mring
 */
public class PhrasesMain {

    public static void main(String[] args) {

        final char[] targetWord = args[0].toCharArray();
        final int generationSize = 100;
        final boolean higherScoreIsBetter = true;
        final EvoContext<Candidate> ctx = new EvoContext<>(generationSize, targetWord.length, higherScoreIsBetter);
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
                // if prev best candidate is any good at all, add it to the next gen
                pop.add(bestCandThisGen);
            }
            genCnt++;
        }

    }
}
