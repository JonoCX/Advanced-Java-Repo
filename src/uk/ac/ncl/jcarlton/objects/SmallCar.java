package uk.ac.ncl.jcarlton.objects;

/**
 * <h1>SmallCar</h1>
 *
 * This class encapsulates the behaviour of a Small
 * Car. Most of the behaviour is inherit, from the
 * {@code Car}.
 *
 * @see uk.ac.ncl.jcarlton.objects.Car
 * @see uk.ac.ncl.jcarlton.objects.AbstractCar
 *
 * @author Jonathan Carlton
 */
public final class SmallCar extends AbstractCar {

    // the consumption rate of the Small Car
    private final static int CONSUMPTION_RATE = 20;

    /**
     * Object constructor that calls {@code AbstractCar}
     * constructor to create a Small Car object.
     * @param registration  the registration of the car.
     */
    public SmallCar(Registration registration) {
        super(registration, 49);
    }

    /**
     * The Small Car uses fuel at a constant consumption
     * rate of 20km per 1lt of fuel.
     *
     * {@inheritDoc}
     */
    @Override
    public int drive(int kmAmount) {
        if (kmAmount < 0)
            // cannot drive a car a negative amount of KMs.
            throw new IllegalArgumentException("Cannot drive a negative amount of KMs: " + kmAmount);

        if (getFuelAmount() < 1)
            // no fuel, so object state is incorrect.
            throw new IllegalStateException("The car has no fuel.");

        if (!(isRented()))
            // the car has to be rented in order to drive it.
            throw new IllegalStateException("The car hasn't been rented.");

        // consumes fuel at the rate of 20km per 1 ltr
        int consumption = kmAmount / CONSUMPTION_RATE;

        // then return the amount of used fuel.
        return useFuel(consumption);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getRegistration().toString() + " (S)";
    }


}
