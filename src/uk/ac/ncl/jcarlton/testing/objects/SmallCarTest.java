package uk.ac.ncl.jcarlton.testing.objects;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ncl.jcarlton.objects.Car;
import uk.ac.ncl.jcarlton.objects.Registration;
import uk.ac.ncl.jcarlton.objects.SmallCar;

import static org.junit.Assert.*;

/**
 * @author Jonathan Carlton
 */
public class SmallCarTest {

    // test objects.
    private Car car;
    private Registration registration;

    /**
     *
     */
    @Before
    public void setUp() {
        registration = Registration.getInstance("SE61", "RLU");
        car = new SmallCar(registration);
    }

    /**
     *
     */
    @Test
    public void driveValid() {
        car.addFuel(10);
        car.setRented(true);
        assertEquals(2, car.drive(40));
        //assertEquals(8, car.getFuelAmount());
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void drivePassNegativeKM() {
        car.drive(-1);
    }

    /**
     *
     */
    @Test(expected = IllegalStateException.class)
    public void driveWithoutFuel() {
        car.drive(10);
    }

    /**
     *
     */
    @Test(expected = IllegalStateException.class)
    public void driveWhenNotRented() {
        car.addFuel(10);
        car.drive(10);
    }

    /**
     *
     */
    @Test
    public void getRegistration() {
        assertEquals(registration, car.getRegistration());
    }

    /**
     *
     */
    @Test
    public void getFuelCapacity() {
        assertEquals(49, car.getFuelCapacity());
    }

    /**
     *
     */
    @Test
    public void getFuelAmount() {
        assertEquals(0, car.getFuelAmount());
    }

    /**
     *
     */
    @Test
    public void isTankFull() {
        assertFalse(car.isTankFull());
    }

    /**
     *
     */
    @Test
    public void addFuelFillTank() {
        car.addFuel(49);
        assertTrue(car.isTankFull());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNegativeAmountOfFuel() {
        car.addFuel(-1);
    }

    @Test
    public void addToAlreadyFullTank() {
        car.addFuel(49);
        assertEquals(0, car.addFuel(10));
    }

    @Test
    public void attemptToOverFillTank() {
        // add 40lt's
        car.addFuel(40);

        // try to add 10 more, should only add 9
        assertEquals(9, car.addFuel(10));

        // check if full
        assertTrue(car.isTankFull());
        assertEquals(49, car.getFuelAmount());
    }

    @Test
    public void addAValidAmountToTank() {
        car.addFuel(25);
        assertEquals(25, car.getFuelAmount());
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void useFuelNegativeAmount() {
        car.useFuel(-1);
    }

    @Test
    public void overUseFuel() {
        car.addFuel(40);

        // used all the fuel in the tank.
        assertEquals(40, car.useFuel(45));

        // tank should be empty.
        assertEquals(0, car.getFuelAmount());
    }

    @Test
    public void useAValidAmountOfFuel() {
        car.addFuel(40);
        assertEquals(20, car.useFuel(20));
        assertEquals(20, car.getFuelAmount());
    }

}