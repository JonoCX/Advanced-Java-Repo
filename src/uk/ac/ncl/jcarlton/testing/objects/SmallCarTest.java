package uk.ac.ncl.jcarlton.testing.objects;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ncl.jcarlton.objects.Car;
import uk.ac.ncl.jcarlton.objects.Registration;
import uk.ac.ncl.jcarlton.objects.SmallCar;

import static org.junit.Assert.*;

/**
 * <h1>JUnit Test Case for Small Car</h1>
 *
 * @see uk.ac.ncl.jcarlton.objects.SmallCar
 *
 * This class tests the common functionality shared
 * between all cars.
 * @see uk.ac.ncl.jcarlton.objects.AbstractCar
 *
 * @author Jonathan Carlton
 */
public class SmallCarTest {

    // test objects.
    private Car car;
    private Registration registration;

    /**
     *  Setup the class test objects
     */
    @Before
    public void setUp() {
        registration = Registration.getInstance("SE61", "RLU");
        car = new SmallCar(registration);
    }

    /**
     *  Test that you're able to drive a valid distance in the
     *  {@code Car} and that the correct amount of fuel (to fill up) is
     *  returned.
     */
    @Test
    public void driveValid() {
        car.addFuel(10);
        car.setRented(true);
        assertEquals(2, car.drive(40));
    }

    /**
     *  Test that a {@code IllegalArgumentException} is
     *  thrown when attempting to drive a negative amount
     *  of km's.
     */
    @Test(expected = IllegalArgumentException.class)
    public void drivePassNegativeKM() {
        car.drive(-1);
    }

    /**
     *  Test that a {@code IllegalArgumentException} is
     *  thrown when attempting to drive the {@code Car} without
     *  fuel.
     */
    @Test(expected = IllegalStateException.class)
    public void driveWithoutFuel() {
        car.useFuel(49);
        car.drive(10);
    }

    /**
     *  Test that a {@code IllegalArgumentException} is
     *  thrown when attempting to drive a {@code Car} when
     *  it hasn't been rented.
     */
    @Test(expected = IllegalStateException.class)
    public void driveWhenNotRented() {
        car.addFuel(10);
        car.drive(10);
    }

    /**
     *  Test that the {@code Registration} is being set properly.
     */
    @Test
    public void getRegistration() {
        assertEquals(registration, car.getRegistration());
    }

    /**
     *  Test that a {@code SmallCar} has a capacity of 49
     */
    @Test
    public void getFuelCapacity() {
        assertEquals(49, car.getFuelCapacity());
    }

    /**
     *  Test that a {@code Car} starts with a full tank.
     */
    @Test
    public void getFuelAmount() {
        assertEquals(49, car.getFuelAmount());
    }

    /**
     *  Test that a {@code Car} has a full tank.
     */
    @Test
    public void isTankFull() {
        assertTrue(car.isTankFull());
    }

    /**
     *  Test that fuel is able to be added to the tank.
     */
    @Test
    public void addFuelToTank() {
        car.useFuel(49);
        assertFalse(car.isTankFull());
        car.addFuel(49);
        assertTrue(car.isTankFull());
    }

    /**
     * Test that a {@code IllegalArgumentException} is thrown
     * when attempting to add a negative amount of fuel to the
     * {@code Car}
     */
    @Test(expected = IllegalArgumentException.class)
    public void addNegativeAmountOfFuel() {
        car.addFuel(-1);
    }

    /**
     * Test that when attempting to add fuel to an already
     * full tank, it returns 0.
     */
    @Test
    public void addToAlreadyFullTank() {
        assertEquals(0, car.addFuel(10));
    }

    /**
     * Test that when attempting to add more than the tank
     * capacity, the overspill is disregarded and the tank
     * is just filled to capacity.
     */
    @Test
    public void attemptToOverFillTank() {
        car.useFuel(49);
        // add 40lt's
        car.addFuel(40);

        // try to add 10 more, should only add 9
        assertEquals(9, car.addFuel(10));

        // check if full
        assertTrue(car.isTankFull());
        assertEquals(49, car.getFuelAmount());
    }

    /**
     * Test that adding a valid amount to the tank
     * is done correctly.
     */
    @Test
    public void addAValidAmountToTank() {
        car.useFuel(40);
        car.addFuel(25);
        assertEquals(34, car.getFuelAmount());
    }

    /**
     *  Test that when attempting to use a negative amount
     *  of fuel a {@code IllegalArgumentException} is thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void useFuelNegativeAmount() {
        car.useFuel(-1);
    }

    /**
     * Test that when attempting to use more fuel than is
     * available in the tank, that it just returns
     * the capacity amount and the tank is set to be empty.
     */
    @Test
    public void overUseFuel() {
        // used all the fuel in the tank.
        assertEquals(49, car.useFuel(50));

        // tank should be empty.
        assertEquals(0, car.getFuelAmount());
    }

    /**
     * Test that using a valid amount of fuel is correct.
     */
    @Test
    public void useAValidAmountOfFuel() {
        assertEquals(20, car.useFuel(20));
        assertEquals(29, car.getFuelAmount());
    }

}