package tests;

import junit.framework.TestCase;

public class SampleTestCase extends TestCase
{
    /**
     * Test of equal method.
     *
     * To run the test case,  look for 'Run SampleTestCase.testEquality' in the menus.
     * The shortcut for IDEA IntelliJ is Fn+Ctrl+Shift+F11
     */
    public void testEquality() {
        int foo = 1 + 1;
        int bar = 2;
        assertEquals("foo and bar values are equal", foo, bar);
    }
}
