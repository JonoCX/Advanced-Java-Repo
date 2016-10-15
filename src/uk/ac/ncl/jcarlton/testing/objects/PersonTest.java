package uk.ac.ncl.jcarlton.testing.objects;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ncl.jcarlton.objects.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * <h1>PersonTest</h1>
 *
 * JUnit test class for the Person object
 *
 * @see uk.ac.ncl.jcarlton.objects.Person
 *
 * @author Jonathan Carlton
 */
public class PersonTest {

    // Variables for testing purposes.
    private Person p1;
    private Person p2;
    private Calendar dob1;
    private Calendar dob2;

    /**
     * Set up the test state variables.
     */
    @Before
    public void setUp()  {
        dob1 = new GregorianCalendar(1993, 8, 27);
        dob2 = new GregorianCalendar(1996, 5, 17);
        p1 = new Person("Jonathan", "Carlton", dob1.getTime());
        p2 = new Person("Joe", "Carlton", dob2.getTime());
    }

    /**
     * Test that when a null or empty first name is passed
     * to the Person constructor it throws an <code>IllegalArgumentException</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsFirstName() {
        Person p = new Person(null, p1.getLastName(), p1.getDateOfBirth());
        Person p2 = new Person("", p1.getLastName(), p1.getDateOfBirth());
    }

    /**
     * Test that when a null or empty last name is passed
     * to the Person constructor it throws an <code>IllegalArgumentException</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsLastName() {
        Person p = new Person(p1.getFirstName(), null, p1.getDateOfBirth());
        Person p2 = new Person(p1.getFirstName(), "", p1.getDateOfBirth());
    }

    /**
     * Test that a <code>IllegalArgumentException</code> is thrown when
     * a null value is passed for the date of birth.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsDateOfBirth() {
        Person p = new Person(p1.getFirstName(), p1.getLastName(),null);
    }

    /**
     * Ensure that the correct String value is being returned
     * when the <code>getFirstName()</code> method is called.
     */
    @Test
    public void getFirstName()  {
        assertEquals("Jonathan", p1.getFirstName());
        assertEquals("Joe", p2.getFirstName());
    }

    /**
     * Ensure that the correct String value is being returned
     * when the <code>getLastName()</code> method is called.
     */
    @Test
    public void getLastName()  {
        assertEquals("Carlton", p1.getLastName());
        assertEquals("Carlton", p2.getLastName());
    }

    /**
     * Ensure that the correct Date object representation is
     * being returned the <code>getDateOfBirth()</code> method
     * is called.
     */
    @Test
    public void getDateOfBirth()  {
        assertEquals(dob1.getTime(), p1.getDateOfBirth());
        assertEquals(dob2.getTime(), p2.getDateOfBirth());
    }

}