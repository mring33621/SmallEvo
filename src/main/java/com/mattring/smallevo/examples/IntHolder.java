
package com.mattring.smallevo.examples;

/**
 * Wraps an int, for use in lambdas, anonymous classes.
 * @author mring
 */
public class IntHolder {
    
    int val = 0;
    public int incr(int i) {
        val += i;
        return val;
    }
    public int decr(int i) {
        val -= i;
        return val;
    }
    public int getVal() {
        return val;
    }
    
}
