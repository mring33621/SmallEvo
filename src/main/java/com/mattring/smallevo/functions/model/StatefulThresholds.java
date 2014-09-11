
package com.mattring.smallevo.functions.model;

/**
 *
 * @author mring
 */
public class StatefulThresholds extends Thresholds {
    
    protected int currentBin;

    public StatefulThresholds(double... values) {
        super(values);
    }

    @Override
    public int findBin(double testVal) {
        this.currentBin = super.findBin(testVal);
        return currentBin;
    }

    public int getCurrentBin() {
        return currentBin;
    }
    
}
