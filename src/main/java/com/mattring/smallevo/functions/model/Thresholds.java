package com.mattring.smallevo.functions.model;

import java.util.Arrays;
import java.util.function.DoubleToIntFunction;
import java.util.function.IntConsumer;

/**
 *
 * @author mring
 */
public class Thresholds implements DoubleToIntFunction {

    protected final double[] values;
    protected final int maxBinIndex;
    protected final double midVal;
    protected final int midIndex;
    protected IntConsumer eventHandler;

    public Thresholds(double... values) {
        // defensive copy
        this.values = Arrays.copyOf(values, values.length);
        Arrays.sort(this.values);
        this.maxBinIndex = values.length + 1;
        this.midIndex = maxBinIndex / 2;
        this.midVal = this.values[midIndex];
    }

    @Override
    public int applyAsInt(double testVal) {
        return findBin(testVal);
    }
    
    public int findBin(double testVal) {
        int i = testVal > midVal ? midIndex + 1 : 0;
        for (; i < values.length; i++) {
            double val = values[i];
            if (testVal <= val) {
                break;
            }
        }
        
        if (eventHandler != null) {
            eventHandler.accept(i);
        }
        
        return i;
    }

    public int getLen() {
        return values.length;
    }

    public int getNumBins() {
        return values.length + 1;
    }

    public int getMaxBinIndex() {
        return maxBinIndex;
    }

    public void setEventHandler(IntConsumer eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Thresholds [maxBinIndex=");
        builder.append(maxBinIndex);
        builder.append(", values=");
        builder.append(Arrays.toString(values));
        builder.append("]");
        return builder.toString();
    }

}
