package uk.ac.ncl.jcarlton.testing.app;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import uk.ac.ncl.jcarlton.app.RentalManagement;
import uk.ac.ncl.jcarlton.objects.*;

import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
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

        // the first small car in the list.
        registrationSmall = Registration.getInstance("SE61", "RLU");

        registrationLarge = Registration.getInstance("TP00", "QQJ");

        carSmall = new SmallCar(registrationSmall);
        carLarge = new LargeCar(registrationLarge);

        management = new RentalManagement();


        management.issueCar(person, license, carSmall);
    }

    @Test
    public void availableCars()  {
        assertEquals(10, management.availableCars(LargeCar.class));
        assertEquals(19, management.availableCars(SmallCar.class));

        // pass a random class type to ensure -1 is returned.
        assertEquals(-1, management.availableCars(String.class));
    }

    @Test
    public void getRentedCars()  {
        // should only have one car rented.
        assertEquals(1, management.getRentedCars().size());

        // check that the car contained in the list is the car here.
        //assertEquals(carSmall, management.getRentedCars().get(0));
    }

    @Test
    public void getCar()  {
        // in the case of testing, the first small car will be issued.
        assertEquals(carSmall, management.getCar(person));
    }

    @Test
    public void issueCar()  {
        fail("not yet implemented");
    }

    @Test(expected = IllegalArgumentException.class)
    public void issueCarNullParameters() {
        management.issueCar(null, license, carSmall);
        management.issueCar(person, null, carSmall);
        management.issueCar(person, license, null);
    }


    @Test
    public void terminateRental() {
        carSmall = management.getCar(person);

        carSmall.drive(40);

        // ensure only 2lts of fuel has been used.
        assertEquals(47, carSmall.getFuelAmount());

        // 2 lts should be required to fill the car
        assertEquals(2, management.terminateRental(person));
    }

    @Test(expected = IllegalArgumentException.class)
    public void terminateRentalNullParameter()  {
        management.terminateRental(null);
    }



    /**
    Passes this test. To run again the method (populateMap()) needs to be
    made public
     */
    @Ignore
    public void populateMapTest()  {
        Map<Car, Person> testMap = new HashMap<>();/*management.populateMap();*/

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
}