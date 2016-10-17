package uk.ac.ncl.jcarlton.objects;

/**
 * @author Jonathan Carlton
 */
public final class LargeCar extends AbstractCar {

    // consumption rates of the Large Car.
    private final static int CONSUMPTION_RATE_1 = 10;
    private final static int CONSUMPTION_RATE_2 = 15;

    /**
     * Object constructor that calls the
     * {@code AbstractCar} constructor to
     * create a car objects.
     * @param registration  the registration of the
     *                      car.
     */
    public LargeCar(Registration registration) {
        super(registration, 60);
    }

    /**
     * The Large Car uses fuel at a slightly
     * different consumption rate than the small
     * car. 10 km per lt for the first 50km and
     * 15 km per ltr for the remaining km's.
     *
     * {@inheritDoc}
     */
    @Override
    public int drive(int kmAmount) {
        if (kmAmount < 0)
            // cannot drive a car a negative amount of KMs.
            throw new IllegalArgumentException("Cannot drive a negative amount of KMs: " + kmAmount);

        if (getFuelAmount() < 1)
            // no fuel, the object state is incorrect.
            throw new IllegalStateException("The car has no fuel.");

        if (!(isRented()))
            // the car has to be rented in order to drive it.
            throw new IllegalStateException("The car hasn't been rented.");

        // attempting to travel more than 50km
        // not >= to avoid divide by 0 error.
        if (kmAmount > 50) {
            // calculate consumption rate for the first 50kms
            int firstFifty = 50 / CONSUMPTION_RATE_1;

            // calculate the consumption rate for the remaining kms
            int remainingKms = (kmAmount - 50) / CONSUMPTION_RATE_2;

            // use the fuel
            return useFuel(firstFifty + remainingKms);
        }

        // else, the kmAmount must be less than 50 so use the normal consumption rate
        int consumption = kmAmount / CONSUMPTION_RATE_1;
        return useFuel(consumption);
    }


}
