package com.mattring.smallevo.functions.model;

import java.util.function.UnaryOperator;

/**
 *
 * @author mring
 */
public class Normalize01 implements UnaryOperator<double[]> {

    @Override
    public double[] apply(double[] da) {
        double[] result = new double[da.length];
        double[] minmax = minmax(da);
        double min = minmax[0];
        double max = minmax[1];
        double scaleFactor = max - min;
        // scaling between [0..1] for starters. Will generalize later.
        for (int x = 0; x < da.length; x++) {
            result[x] = ((da[x] - min) / scaleFactor);
        }
        return result;
    }

    double[] minmax(double[] da) {
        double min = da[0];
        double max = min;
        for (int x = 1; x < da.length; x++) {
            double curr = da[x];
            if (curr < min) {
                min = curr;
            } else if (curr > max) {
                max = curr;
            }
        }
        return new double[]{min, max};
    }

}
