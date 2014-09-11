
package com.mattring.smallevo;

import java.util.Comparator;

/**
 *
 * @author mring
 */
public class EvoContext<T extends Candidate> {
    
    private final int generationSz;
    private final int genomeSz;
    private final boolean higherScoreIsBetter;

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
    
    public Comparator<T> getScoreComparator() {
        Comparator<T> comp;
        comp = Comparator.comparing(T::getScore);
        if (!higherScoreIsBetter) {
            comp = comp.reversed();
        }
        return comp;
    }
    
}
