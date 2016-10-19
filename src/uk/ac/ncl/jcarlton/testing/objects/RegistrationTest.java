package uk.ac.ncl.jcarlton.testing.objects;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ncl.jcarlton.objects.Registration;

import static org.junit.Assert.assertEquals;

/**
 * <h1>JUnit test class for {@link Registration}</h1>
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
        registration = Registration.getInstance("NG57", "HXE");
    }

    /**
     * Test that a {@code IllegalArgumentException} is
     * thrown when the regex isn't matched.
     * @see uk.ac.ncl.jcarlton.objects.Registration#getInstance(String, String)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFirstComponentThrowsLength() {
        exceptionRegistrationTester = Registration.getInstance("ABC", "HXE");
    }

    /**
     * Test that a {@code NullPointerException} is
     * thrown when the a null instance is passed for the
     * first component.
     * @see uk.ac.ncl.jcarlton.objects.Registration#getInstance(String, String)
     */
    @Test(expected = NullPointerException.class)
    public void testFirstComponentThrowsNull() {
        exceptionRegistrationTester = Registration.getInstance(null, "HXE");
    }

    /**
     * Test that a {@code IllegalArgumentException} is
     * thrown if an empty string is passed for the first
     * component.
     * @see uk.ac.ncl.jcarlton.objects.Registration#getInstance(String, String)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFirstComponentThrowsIsEmpty() {
        exceptionRegistrationTester = Registration.getInstance("", "HXE");
    }

    /**
     * Test that a {@code IllegalArgumentException} is
     * thrown if, even after trimming, the string is
     * still empty.
     * @see uk.ac.ncl.jcarlton.objects.Registration#getInstance(String, String)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFirstComponentThrowsTrimEmpty() {
        exceptionRegistrationTester = Registration.getInstance("  ", "HXE");
    }

    /**
     * Test that a {@code IllegalArgumentException} is
     * thrown when the length of the second component is
     * not equal to 2.
     * @see uk.ac.ncl.jcarlton.objects.Registration#getInstance(String, String)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSecondComponentThrowsLength() {
        exceptionRegistrationTester = Registration.getInstance("NG57", "HXEL");
    }

    /**
     * Test that a {@code IllegalArgumentException} is
     * thrown when the length of the third component is
     * not equal to 3.
     * @see uk.ac.ncl.jcarlton.objects.Registration#getInstance(String, String)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testThirdComponentThrowsLength() {
        exceptionRegistrationTester = Registration.getInstance("NG57", "HX");
    }

    /**
     * Test that a {@code NullPointerException} is
     * thrown when the a null instance is passed for the
     * third component.
     * @see uk.ac.ncl.jcarlton.objects.Registration#getInstance(String, String)
     */
    @Test(expected = NullPointerException.class)
    public void testThirdComponentThrowsNull() {
        exceptionRegistrationTester = Registration.getInstance("NG57", null);
    }

    /**
     * Test that a {@code IllegalArgumentException} is
     * thrown if an empty string is passed for the third
     * component.
     * @see uk.ac.ncl.jcarlton.objects.Registration#getInstance(String, String)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testThirdComponentThrowsIsEmpty() {
        exceptionRegistrationTester = Registration.getInstance("NG57", "");
    }

    /**
     * Test that a {@code IllegalArgumentException} is
     * thrown if, even after trimming, the string is
     * still empty.
     * @see uk.ac.ncl.jcarlton.objects.Registration#getInstance(String, String)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testThirdComponentThrowsTrimEmpty() {
        exceptionRegistrationTester = Registration.getInstance("NG57", "  ");
    }

    /**
     * Test that the {@code toString()} representation
     * is correct.
     * @see uk.ac.ncl.jcarlton.objects.Registration#toString()
     * @see java.lang.Object#toString()
     */
    @Test
    public void testToString() {
        assertEquals("NG57 HXE", registration.toString());
    }

    /**
     * Test that the first component is being set properly.
     * @see uk.ac.ncl.jcarlton.objects.Registration#getFirstComponent()
     */
    @Test
    public void getFirstComponent() {
        assertEquals("NG57", registration.getFirstComponent());
    }

    /**
     * Test that the second component is being set properly.
     * @see uk.ac.ncl.jcarlton.objects.Registration#getSecondComponent()
     */
    @Test
    public void getSecondComponent()  {
        assertEquals("HXE", registration.getSecondComponent());
    }


}