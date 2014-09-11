
package com.mattring.smallevo;

import java.util.Comparator;

/**
 * General config for and evolutionary process
 * @author mring
 */
public class EvoContext<T extends Candidate> {
    
    private final int generationSz;
    private final int genomeSz;
    private final boolean higherScoreIsBetter;

    /**
     * 
     * @param generationSz -- num individuals per generation
     * @param genomeSz -- length of an individual's genome
     * @param higherScoreIsBetter -- set true for maximization, false for minimization
     */
    public EvoContext(int generationSz, int genomeSz, boolean higherScoreIsBetter) {
        this.generationSz = generationSz;
        this.genomeSz = genomeSz;
        this.higherScoreIsBetter = higherScoreIsBetter;
    }

    public int getGenerationSz() {
        return generationSz;
    }

    public int getGenomeSz() {
        return genomeSz;
    }

    public boolean isHigherScoreIsBetter() {
        return higherScoreIsBetter;
    }
    
    /**
     * 
     * @return comparator to rank candidates by score
     */
    public Comparator<T> getScoreComparator() {
        Comparator<T> comp;
        comp = Comparator.comparing(T::getScore);
        if (!higherScoreIsBetter) {
            comp = comp.reversed();
        }
        return comp;
    }
    
}
