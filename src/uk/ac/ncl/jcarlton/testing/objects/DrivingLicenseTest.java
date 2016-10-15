package uk.ac.ncl.jcarlton.testing.objects;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ncl.jcarlton.objects.DrivingLicense;
import uk.ac.ncl.jcarlton.objects.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

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
    public void getInstance() {

    }

    /**
     *
     */
    @Test
    public void testToString() {
        String test = "JC-2011-" + license.getThirdComponent();
        assertEquals(test, license.toString());
    }

    /**
     *
     */
    @Test
    public void getFirstComponent() {

    }

    /**
     *
     */
    @Test
    public void getSecondComponent() {

    }

    /**
     *
     */
    @Test
    public void getThirdComponent() {

    }

    /**
     *
     */
    @Test
    public void isFullLicense() {

    }

    /**
     *
     */
    @Test
    public void processName() {

    }

    /**
     *
     */
/*    @Test
    public void generateSerial() {
        assertTrue(DrivingLicense.generateSerial() > 0);
        assertTrue(DrivingLicense.generateSerial() < 100);
    }*/
}