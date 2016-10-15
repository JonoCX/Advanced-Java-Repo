package uk.ac.ncl.jcarlton.testing.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import uk.ac.ncl.jcarlton.objects.Registration;

/**
 * <h1>RegistrationTest</h1>
 *
 * JUnit test class for the Registration object.
 *
 * @see uk.ac.ncl.jcarlton.objects.Registration
 *
 * @author Jonathan Carlton
 */
public class RegistrationTest {

    // variables for testing purposes
    private Registration registration;
    private Registration exceptionRegistrationTester;

    /**
     * Set up the test state variables
     */
    @Before
    public void setUp()  {
        // get instance is tested every time the code is ran.
        registration = Registration.getInstance("NG", 57, "HXE");
    }

    /**
     * Test that a <code>IllegalArgumentException</code> is
     * thrown when the length of the first component is
     * not equal to 2.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFirstComponentThrowsLength() {
        exceptionRegistrationTester = Registration.getInstance("ABC", 10, "HXE");
    }

    /**
     * Test that a <code>NullPointerException</code> is
     * thrown when the a null instance is passed for the
     * first component.
     */
    @Test(expected = NullPointerException.class)
    public void testFirstComponentThrowsNull() {
        exceptionRegistrationTester = Registration.getInstance(null, 10, "HXE");
    }

    /**
     * Test that a <code>IllegalArgumentException</code> is
     * thrown if an empty string is passed for the first
     * component.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFirstComponentThrowsIsEmpty() {
        exceptionRegistrationTester = Registration.getInstance("", 10, "HXE");
    }

    /**
     * Test that a <code>IllegalArgumentException</code> is
     * thrown if, even after trimming, the string is
     * still empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFirstComponentThrowsTrimEmpty() {
        exceptionRegistrationTester = Registration.getInstance("  ", 10, "HXE");
    }

    /**
     * Test that a <code>IllegalArgumentException</code> is
     * thrown when the length of the second component is
     * not equal to 2.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSecondComponentThrowsLength() {
        exceptionRegistrationTester = Registration.getInstance("NG", 100, "HXE");
    }

    /**
     * Test that a <code>IllegalArgumentException</code> is
     * thrown when the length of the third component is
     * not equal to 3.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testThirdComponentThrowsLength() {
        exceptionRegistrationTester = Registration.getInstance("NG", 57, "HX");
    }

    /**
     * Test that a <code>NullPointerException</code> is
     * thrown when the a null instance is passed for the
     * third component.
     */
    @Test(expected = NullPointerException.class)
    public void testThirdComponentThrowsNull() {
        exceptionRegistrationTester = Registration.getInstance("NG", 10, null);
    }

    /**
     * Test that a <code>IllegalArgumentException</code> is
     * thrown if an empty string is passed for the third
     * component.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testThirdComponentThrowsIsEmpty() {
        exceptionRegistrationTester = Registration.getInstance("NG", 10, "");
    }

    /**
     * Test that a <code>IllegalArgumentException</code> is
     * thrown if, even after trimming, the string is
     * still empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testThirdComponentThrowsTrimEmpty() {
        exceptionRegistrationTester = Registration.getInstance("NG", 10, "  ");
    }

    /**
     * Test that the <code>toString</code> representation
     * is correct.
     * @see java.lang.Object#toString()
     */
    @Test
    public void testToString() {
        assertEquals("NG57 HXE", registration.toString());
    }

    /**
     * Test that the first component is being set properly.
     */
    @Test
    public void getFirstComponent() {
        assertEquals("NG", registration.getFirstComponent());
    }

    /**
     * Test that the second component is being set properly.
     */
    @Test
    public void getSecondComponent()  {
        assertEquals(57, registration.getSecondComponent());
    }

    /**
     * Test that the third component is being set properly.
     */
    @Test
    public void getThirdComponent() {
        assertEquals("HXE", registration.getThirdComponent());
    }

}