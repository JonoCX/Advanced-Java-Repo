package uk.ac.ncl.jcarlton.testing.app;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.Before;
import org.junit.Test;
import uk.ac.ncl.jcarlton.app.RentalManagement;
import uk.ac.ncl.jcarlton.objects.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Jonathan Carlton
 *         17/10/2016
 */
public class RentalManagementTest {

    private RentalManagement management;

    private Person person;
    private DrivingLicense license;

    private Car carLarge;
    private Car carSmall;
    private Registration registrationSmall;
    private Registration registrationLarge;

    private Calendar dob;
    private Calendar licenseDate;

    @Before
    public void setUp()  {
        dob = new GregorianCalendar(1993, 8, 27);
        licenseDate = new GregorianCalendar(2011, 5, 28);
        person = new Person(
                "Jonathan",
                "Carlton",
                dob.getTime(),
                true,
                licenseDate.getTime()
        );

        license = person.getLicense();
        registrationSmall = Registration.getInstance("NG57", "HXE");
        registrationLarge = Registration.getInstance("LA01", "VHW");

        management = new RentalManagement();

        carSmall = new SmallCar(registrationSmall);
        carLarge = new LargeCar(registrationLarge);

        management.issueCar(person, license, carSmall);
    }

    @Test
    public void availableCars()  {
        assertEquals(10, management.availableCars(LargeCar.class));
        assertEquals(20, management.availableCars(SmallCar.class));

        // pass a random class type to ensure -1 is returned.
        assertEquals(-1, management.availableCars(String.class));
    }

    @Test
    public void getRentedCars()  {

    }

    @Test
    public void getCar()  {
        assertEquals(carSmall, management.getCar(person));
    }

    @Test
    public void issueCar()  {

    }

    @Test
    public void terminateRental()  {

    }

/*
    Passes this test. To run again the method (populateMap()) needs to be
    made public
    @Test
    public void populateMapTest()  {
        Map<Car, Person> testMap = management.populateMap();

        int largeCount = 0;
        int smallCount = 0;
        int nullCount = 0;

        for (Map.Entry<Car, Person> m : testMap.entrySet()) {
            if (m.getKey() instanceof LargeCar) largeCount++;
            if (m.getKey() instanceof SmallCar) smallCount++;
            if (m.getValue() == null) nullCount++;
        }

        assertEquals(10, largeCount);
        assertEquals(20, smallCount);
        assertEquals(30, nullCount);
    }
*/
}