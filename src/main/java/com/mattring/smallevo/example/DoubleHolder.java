
package com.mattring.smallevo.example;

/**
 *
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
