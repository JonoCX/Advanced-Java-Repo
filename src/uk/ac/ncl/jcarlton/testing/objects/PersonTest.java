package uk.ac.ncl.jcarlton.testing.objects;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ncl.jcarlton.objects.Person;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * <h1>JUnit test class for {@link Person}</h1>
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
    private Calendar licenceIssue1;
    private Calendar licenseIssue2;

    /**
     * Set up the test state variables.
     */
    @Before
    public void setUp()  {
        dob1 = new GregorianCalendar(1993, 8, 27);
        dob2 = new GregorianCalendar(1996, 5, 17);
        licenceIssue1 = new GregorianCalendar(2011, 5, 28);
        licenseIssue2 = new GregorianCalendar(2014, 5, 28);
        p1 = new Person("Jonathan", "Carlton", dob1.getTime(), true, licenceIssue1.getTime());
        p2 = new Person("Joe", "Carlton", dob2.getTime(), false, licenseIssue2.getTime());
    }

    /**
     * Test that when a null or empty first name is passed
     * to the Person constructor it throws an {@code IllegalArgumentException}.
     *
     * @see uk.ac.ncl.jcarlton.objects.Person#Person(String, String, Date, boolean, Date)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsFirstName() {
        Person p = new Person(null, p1.getLastName(), p1.getDateOfBirth(), true, licenceIssue1.getTime());
        Person p2 = new Person("", p1.getLastName(), p1.getDateOfBirth(), false, licenseIssue2.getTime());
    }

    /**
     * Test that when a null or empty last name is passed
     * to the Person constructor it throws an {@code IllegalArgumentException}.
     *
     * @see uk.ac.ncl.jcarlton.objects.Person#Person(String, String, Date, boolean, Date)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsLastName() {
        Person p = new Person(p1.getFirstName(), null, p1.getDateOfBirth(), true, licenceIssue1.getTime());
        Person p2 = new Person(p1.getFirstName(), "", p1.getDateOfBirth(), false, licenseIssue2.getTime());
    }

    /**
     * Test that a {@code IllegalArgumentException} is thrown when
     * a null value is passed for the date of birth.
     *
     * @see uk.ac.ncl.jcarlton.objects.Person#Person(String, String, Date, boolean, Date)
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsDateOfBirth() {
        Person p = new Person(p1.getFirstName(), p1.getLastName(),null, true, licenceIssue1.getTime());
    }

    /**
     * Ensure that the correct String value is being returned
     * when the {@code getFirstName()} method is called.
     *
     * @see uk.ac.ncl.jcarlton.objects.Person#getFirstName()
     */
    @Test
    public void getFirstName()  {
        assertEquals("Jonathan", p1.getFirstName());
        assertEquals("Joe", p2.getFirstName());
    }

    /**
     * Ensure that the correct String value is being returned
     * when the {@code getLastName()} method is called.
     *
     * @see uk.ac.ncl.jcarlton.objects.Person#getLastName()
     */
    @Test
    public void getLastName()  {
        assertEquals("Carlton", p1.getLastName());
        assertEquals("Carlton", p2.getLastName());
    }

    /**
     * Ensure that the correct Date object representation is
     * being returned the {@code getDateOfBirth()} method
     * is called.
     *
     * @see uk.ac.ncl.jcarlton.objects.Person#getDateOfBirth()
     */
    @Test
    public void getDateOfBirth()  {
        assertEquals(dob1.getTime(), p1.getDateOfBirth());
        assertEquals(dob2.getTime(), p2.getDateOfBirth());
    }

    /**
     * Test that the {@code valueOf()} method correctly
     * parses a String that is made up of the Person
     * components.
     *
     * @see uk.ac.ncl.jcarlton.objects.Person#valueOf(String)
     * @throws ParseException thrown when the date cannot be
     * correctly parsed.
     */
    @Test
    public void testValueOf() throws ParseException {
        String personString = p1.toString();
        System.out.println(personString);
        Person p1New = Person.valueOf(personString);
        assertEquals(p1, p1New);
    }
}