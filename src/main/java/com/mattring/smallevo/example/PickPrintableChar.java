
package com.mattring.smallevo.example;

/**
 *
 * @author mring
 */
public class PickPrintableChar {
    
    public char pick(double d) {
        final char printableChar = (char) ( (d * 91) + 32 );
        return printableChar;
    }
    
}
