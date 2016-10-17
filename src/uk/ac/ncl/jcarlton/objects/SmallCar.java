package uk.ac.ncl.jcarlton.objects;

/**
 * @author Jonathan Carlton
 */
public class SmallCar extends AbstractCar {

    private final static int CONSUMPTION_RATE = 20;
    private boolean rented;

    public SmallCar(Registration registration) {
        super(registration, 49);
    }

    @Override
    public int drive(int kmAmount) {
        if (kmAmount < 0)
            // cannot drive a car a negative amount of KMs.
            throw new IllegalArgumentException("Cannot drive a negative amount of KMs: " + kmAmount);

        if (getFuelAmount() < 1)
            // no fuel, so object state is incorrect.
            throw new IllegalStateException("The car has no fuel.");

        // consumes fuel at the rate of 20km per 1 ltr
        int consumption = kmAmount / CONSUMPTION_RATE;

        System.out.println("Consumption: " + consumption);

        return useFuel(consumption);
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
