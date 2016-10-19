package uk.ac.ncl.jcarlton.testing.objects;

import org.junit.Before;
import org.junit.Test;
import uk.ac.ncl.jcarlton.objects.Car;
import uk.ac.ncl.jcarlton.objects.LargeCar;
import uk.ac.ncl.jcarlton.objects.Registration;

import static org.junit.Assert.assertEquals;

/**
 * <h1>JUnit test class for {@link LargeCar}</h1>
 *
 * Most of the common functionality of a car is
 * tested in the {@code SmallCarTest} class.
 * @see uk.ac.ncl.jcarlton.testing.objects.SmallCarTest
 *
 * @author Jonathan Carlton
 */
public class LargeCarTest {

    // test objects.
    private Car car;
    private Registration registration;

    /**
     * Setup method to initialise the test objects within
     * the class.
     */
    @Before
    public void setUp() {
        registration = Registration.getInstance("TP00", "QQJ");
        car = new LargeCar(registration);
    }

    /**
     * Test that you're able to drive the car a valid
     * amount and the amount of fuel used returned is
     * correct.
     *
     * @see uk.ac.ncl.jcarlton.objects.LargeCar#drive(int)
     */
    @Test
    public void driveValidUnder50km()  {
        car.setRented(true);
        assertEquals(2, car.drive(20));
    }

    /**
     * Test that when driving over 50km, the correct
     * amount of fuel is used and the required amount
     * to re-fill the car is returned.
     *
     * @see uk.ac.ncl.jcarlton.objects.LargeCar#drive(int)
     */
    @Test
    public void driveValidOver50km() {
        car.setRented(true);

        assertEquals(6, car.drive(60));
    }

    /**
     * Test that if you pass a negative number to the
     * method, it'll throw a {@code IllegalArgumentException}
     *
     * @see uk.ac.ncl.jcarlton.objects.LargeCar#drive(int)
     */
    @Test(expected = IllegalArgumentException.class)
    public void driveNegativeKm() {
        car.drive(-1);
    }

    /**
     * Test that if you attempt to drive the car without
     * it having an fuel that a {@code IllegalStateException}
     * is thrown.
     *
     * @see uk.ac.ncl.jcarlton.objects.LargeCar#drive(int)
     */
    @Test(expected = IllegalStateException.class)
    public void driveWithoutFuel() {
        // all cars start with a full tank
        car.useFuel(60);
        car.drive(10);
    }

    /**
     * Test that if you attempt to drive the car before
     * it has been rented, an {@code IllegalStateException}
     * is thrown.
     *
     * @see uk.ac.ncl.jcarlton.objects.LargeCar#drive(int)
     */
    @Test(expected = IllegalStateException.class)
    public void driveWhenNotRented() {
        car.drive(10);
    }

    /**
     * Test that the {@code toString} method is
     * correctly constructing the string representation
     * of the Large Car object.
     *
     * @see uk.ac.ncl.jcarlton.objects.LargeCar#toString()
     * @see java.lang.Object#toString()
     */
    @Test
    public void testToString()  {
        String test = "TP00 QQJ (L)";
        assertEquals(test, car.toString());
    }

}