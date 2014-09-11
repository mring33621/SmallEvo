
package com.mattring.smallevo.examples;

/**
 * Wraps a double, for use in lambdas, anonymous classes.
 * @author mring
 */
public class DoubleHolder {
    
    double val = 0d;
    public double incr(double d) {
        val += d;
        return val;
    }
    public double decr(double d) {
        val -= d;
        return val;
    }
    public double getVal() {
        return val;
    }
    
}
