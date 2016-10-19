package uk.ac.ncl.jcarlton.testing.app;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import uk.ac.ncl.jcarlton.app.RentalManagement;
import uk.ac.ncl.jcarlton.objects.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * <h1>JUnit test class for {@link RentalManagement}</h1>
 *
 * @author Jonathan Carlton
 */
public class RentalManagementTest {

    /*
        Test member variables
     */
    private RentalManagement management;

    private Person person;
    private DrivingLicense license;

    private Car carLarge;
    private Car carSmall;
    private Registration registrationSmall;
    private Registration registrationLarge;

    private Calendar dob;
    private Calendar licenseDate;

    /**
     * Setup the test objects
     */
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

        registrationSmall = Registration.getInstance("SE61", "RLU");

        registrationLarge = Registration.getInstance("TP00", "QQJ");

        carSmall = new SmallCar(registrationSmall);
        carLarge = new LargeCar(registrationLarge);

        management = new RentalManagement();


        management.issueCar(person, license, carSmall);
    }

    /**
     * Test that there are the correct number of available cars in the
     * car rental system.
     *
     * @see uk.ac.ncl.jcarlton.app.RentalManagement#availableCars(Class)
     */
    @Test
    public void availableCars()  {
        assertEquals(10, management.availableCars(LargeCar.class));
        // 19 as one is rented in the setup.
        assertEquals(19, management.availableCars(SmallCar.class));

        // pass a random class type to ensure -1 is returned.
        assertEquals(-1, management.availableCars(String.class));
    }

    /**
     * Test that the correct number of cars currently rented is return.
     *
     * @see uk.ac.ncl.jcarlton.app.RentalManagement#getRentedCars()
     */
    @Test
    public void getRentedCars()  {
        // should only have one car rented.
        assertEquals(1, management.getRentedCars().size());

        // check that the car contained in the list is the car here.
        assertEquals(carSmall, management.getRentedCars().get(0));
    }

    /**
     * Test that the car which is rented in the {@code setUp()} is
     * fetchable, given the Person.
     *
     * @see uk.ac.ncl.jcarlton.app.RentalManagement#getCar(Person)
     */
    @Test
    public void getCar()  {
        // in the case of testing, the first small car will be issued.
        assertEquals(carSmall, management.getCar(person));
    }

    /**
     * Test that you are able to successful issue a large car to
     * a person.
     *
     * @see uk.ac.ncl.jcarlton.app.RentalManagement#issueCar(Person, DrivingLicense, Car)
     */
    @Test
    public void issueCar()  {
        // test for large car, small car is proved to work in the setUp()
        Calendar oldDob = new GregorianCalendar(1985, 8, 27);
        Calendar oldLicenseDate = new GregorianCalendar(2000, 7, 19);
        person = new Person(
                "Jonathan",
                "Carlton",
                oldDob.getTime(),
                true,
                oldLicenseDate.getTime()
        );
        final DrivingLicense dl = person.getLicense();
        assertTrue(management.issueCar(person, dl, carLarge));
    }

    /**
     * Test that when null parameters are passed to the method
     * that it throws an {@code IllegalArgumentException}
     *
     * @see uk.ac.ncl.jcarlton.app.RentalManagement#issueCar(Person, DrivingLicense, Car)
     */
    @Test(expected = IllegalArgumentException.class)
    public void issueCarNullParameters() {
        management.issueCar(null, license, carSmall);
        management.issueCar(person, null, carSmall);
        management.issueCar(person, license, null);
    }

    /**
     * Test that the {@code false} is returned by the method in
     * the various different scenarios listed in the method
     * description.
     *
     * @see uk.ac.ncl.jcarlton.app.RentalManagement#issueCar(Person, DrivingLicense, Car)
     */
    @Test
    public void issueCarReturnFalseCases() {
        // setup new variables for the test(s).
        Calendar youngDob = new GregorianCalendar(1997, 8, 27);
        Calendar licenceDate = new GregorianCalendar(2014, 5, 17);
        Person p2 = new Person(
                "Joe",
                "Carlton",
                dob.getTime(),
                false,
                licenceDate.getTime()
        );
        final DrivingLicense dl = p2.getLicense();

        // license doesn't belong to them
        assertFalse("License doesn't belong to.", management.issueCar(person, dl, carSmall));

        // already renting a car
        assertFalse("Already renting a car.", management.issueCar(person, license, carSmall));

        management.terminateRental(person);

        // doesn't have a full license
        assertFalse("Doesn't have a full license", management.issueCar(p2, dl, carSmall));

        // car tank isn't full
        carLarge.useFuel(2);
        assertFalse("Car tank isn't full", management.issueCar(p2, dl, carLarge));

        carLarge.addFuel(2);
        assertTrue(carLarge.isTankFull());

        // isn't old enough for large car.
        assertFalse("Isn't old enough for large car.", management.issueCar(person, license, carLarge));

        licenceDate = new GregorianCalendar(2016, 9, 1);
        p2 = new Person(
                "Joe",
                "Carlton",
                youngDob.getTime(),
                true,
                licenceDate.getTime()
        );
        // hasn't held license for long enough - small car.
        assertFalse("Small Car: hasn't held license for long enough", management.issueCar(p2, p2.getLicense(), carSmall));

        Calendar oldDob = new GregorianCalendar(1980, 5, 17);
        p2 = new Person(
                "Joe",
                "Carlton",
                oldDob.getTime(),
                true,
                licenceDate.getTime()
        );
        // hasn't held license for long enough - large car.
        assertFalse("Large Car: hasn't held license for long enough", management.issueCar(p2, p2.getLicense(), carLarge));
    }

    /**
     * Test that the termination of a rental agreement is
     * working correctly.
     *
     * @see uk.ac.ncl.jcarlton.app.RentalManagement#terminateRental(Person)
     */
    @Test
    public void terminateRental() {
        carSmall = management.getCar(person);

        carSmall.drive(40);

        // ensure only 2lts of fuel has been used.
        assertEquals(47, carSmall.getFuelAmount());

        // 2 lts should be required to fill the car
        assertEquals(2, management.terminateRental(person));
    }

    /**
     * Test that a {@code IllegalArgumentException} is thrown
     * when a null parameter is passed to the method
     *
     * @see uk.ac.ncl.jcarlton.app.RentalManagement#terminateRental(Person)
     */
    @Test(expected = IllegalArgumentException.class)
    public void terminateRentalNullParameter()  {
        management.terminateRental(null);
    }


    /**
     * Passes this test. To run again the method (populateMap()) needs to be made public
     *
     * @see uk.ac.ncl.jcarlton.app.RentalManagement#populateMap()
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