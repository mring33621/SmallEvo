
package com.mattring.smallevo.functions.model;

import java.util.function.IntConsumer;

/**
 *
 * @author mring
 */
public class InfluenceLink implements IntConsumer {
    private final int[] influences;
    private final AdjustableThresholds target;

    public InfluenceLink(int[] influences, AdjustableThresholds target) {
        this.influences = influences;
        this.target = target;
    }

    @Override
    public void accept(int value) {
        target.setAdjustment(influences[value]);
    }
    
}
