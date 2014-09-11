
package com.mattring.smallevo.examples.phrases;

/**
 * Picks a printable ASCII char based on the incoming double.
 * @author mring
 */
public class PickPrintableChar {
    
    /**
     * 
     * @param d, should be in range (0.0d and 1.0d)
     * @return char
     */
    public char pick(double d) {
        final char printableChar = (char) ( (d * 91) + 32 );
        return printableChar;
    }
    
}
