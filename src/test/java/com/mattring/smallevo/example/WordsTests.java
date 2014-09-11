
package com.mattring.smallevo.example;

import org.junit.Test;

/**
 *
 * @author mring
 */
public class WordsTests {
    
    @Test
    public void testPickPrintableChar() {
        PickPrintableChar ppc = new PickPrintableChar();
        for (double d = 0d; d < 1d; d = d + 0.005) {
            System.out.println(ppc.pick(d));
        }
    }
    
}
