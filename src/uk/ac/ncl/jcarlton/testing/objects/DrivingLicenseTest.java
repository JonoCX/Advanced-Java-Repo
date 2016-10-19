package uk.ac.ncl.jcarlton.testing.objects;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import uk.ac.ncl.jcarlton.objects.DrivingLicense;
import uk.ac.ncl.jcarlton.objects.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * <h1>DrivingLicenceTest</h1>
 *
 * JUnit test class for the Driving License class.
 *
 * @see uk.ac.ncl.jcarlton.objects.DrivingLicense
 *
 * @author Jonathan Carlton
 */
public class DrivingLicenseTest {

    private DrivingLicense license;

    /**
     *  Setup the test objects
     */
    @Before
    public void setUp() {
        Calendar calendar = new GregorianCalendar(1993, 8, 27);
        Calendar calendar1 = new GregorianCalendar(2011, 1, 1);
        license = DrivingLicense.getInstance(
                new Person("Jonathan", "Carlton", calendar.getTime(), true,  calendar1.getTime()),
                calendar1.getTime(),
                true
        );
    }

    /**
     *  Test that the {@code toString} method is
     *  constructing the String representation
     *  of a Driving license correctly.
     *
     *  @see uk.ac.ncl.jcarlton.objects.DrivingLicense#toString()
     */
    @Test
    public void testToString() {
        String expected = "JC-2011-" + license.getThirdComponent() + "-true";
        assertEquals(expected, license.toString());
    }

    /**
     *  Test that the first component of the
     *  license is being set and fetched
     *  correctly.
     *
     *  @see uk.ac.ncl.jcarlton.objects.DrivingLicense#getFirstComponent()
     */
    @Test
    public void getFirstComponent() {
        assertEquals("JC", license.getFirstComponent());
    }

    /**
     *  Test that the second component of the
     *  license is being set and fetched
     *  correctly.
     *
     *  @see uk.ac.ncl.jcarlton.objects.DrivingLicense#getSecondComponent()
     */
    @Test
    public void getSecondComponent() {
        Calendar calendar = new GregorianCalendar(2011, 1, 1);
        assertEquals(calendar.getTime(), license.getSecondComponent());
    }

    /**
     *  Test that the full license is being set
     *  properly.
     *
     *  @see uk.ac.ncl.jcarlton.objects.DrivingLicense#isFullLicense()
     */
    @Test
    public void isFullLicense() {
        assertTrue(license.isFullLicense());
    }


    @Ignore
    public void processName() {
        // processName needs to be made public for it to work
//        Calendar calendar = new GregorianCalendar(1993, 8, 27);
//        Person p = new Person("Jonathan", "Carlton", calendar.getTime());
//        String expected = "JC";
//        assertEquals(expected, DrivingLicense.processName(p));
    }


    @Ignore
    public void generateSerial() {
        // method needs to be made public for it to work.
        //assertTrue(DrivingLicense.generateSerial() > 0);
        // assertTrue(DrivingLicense.generateSerial() < 100);
    }
}