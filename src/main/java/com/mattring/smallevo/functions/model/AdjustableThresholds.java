
package com.mattring.smallevo.functions.model;

/**
 *
 * @author mring
 */
public class AdjustableThresholds extends StatefulThresholds {
    
    private Integer adjustment = null;

    public void setAdjustment(Integer adjustment) {
        this.adjustment = adjustment;
    }

    public boolean hasAdjustment() {
        return adjustment != null && adjustment > 0;
    }

    private int adjust() {
        if (hasAdjustment()) {
            final int mod = getLen();
            final int rawNewBin = this.currentBin + adjustment;
            currentBin = ((rawNewBin % mod) + mod) % mod;
            adjustment = null;
        }
        return this.currentBin;
    }

    @Override
    public int getCurrentBin() {
        return adjust();
    }
    
}
