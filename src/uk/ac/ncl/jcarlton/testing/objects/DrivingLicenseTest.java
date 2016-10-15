package uk.ac.ncl.jcarlton.testing.objects;

import org.junit.Before;
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
     *
     */
    @Before
    public void setUp() {
        Calendar calendar = new GregorianCalendar(1993, 8, 27);
        Calendar calendar1 = new GregorianCalendar(2011, 1, 1);
        license = DrivingLicense.getInstance(
                new Person("Jonathan", "Carlton", calendar.getTime()),
                calendar1.getTime(),
                true
        );
    }

    /**
     *
     */
    @Test
    public void testToString() {
        String expected = "JC-2011-" + license.getThirdComponent();
        assertEquals(expected, license.toString());
    }

    /**
     *
     */
    @Test
    public void getFirstComponent() {
        assertEquals("JC", license.getFirstComponent());
    }

    /**
     *
     */
    @Test
    public void getSecondComponent() {
        Calendar calendar = new GregorianCalendar(2011, 1, 1);
        assertEquals(calendar.getTime(), license.getSecondComponent());
    }

    /**
     *
     */
    @Test
    public void isFullLicense() {
        assertTrue(license.isFullLicense());
    }

    /**
     *
     */
/*    @Test
    public void processName() {
        Calendar calendar = new GregorianCalendar(1993, 8, 27);
        Person p = new Person("Jonathan", "Carlton", calendar.getTime());
        String expected = "JC";
        assertEquals(expected, DrivingLicense.processName(p));
    }*/

    /**
     *
     */
/*    @Test
    public void generateSerial() {
        assertTrue(DrivingLicense.generateSerial() > 0);
        assertTrue(DrivingLicense.generateSerial() < 100);
    }*/
}