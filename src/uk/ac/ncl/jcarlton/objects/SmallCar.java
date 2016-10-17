package uk.ac.ncl.jcarlton.objects;

/**
 * @author Jonathan Carlton
 */
public class SmallCar extends AbstractCar {

    private final static int CONSUMPTION_RATE = 20;

    /**
     *
     * @param registration
     */
    public SmallCar(Registration registration) {
        super(registration, 49);
    }

    /**
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

    @Override
    public String toString() {
        return getRegistration().toString() + " (S)";
    }


}
