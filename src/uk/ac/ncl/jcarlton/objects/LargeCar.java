package uk.ac.ncl.jcarlton.objects;

/**
 * @author Jonathan Carlton
 */
public class LargeCar extends AbstractCar {

    private boolean rented;

    public LargeCar(Registration registration) {
        super(registration, 60);
    }

    @Override
    public int drive(int kmAmount) {
        if (kmAmount < 0)
            throw new IllegalArgumentException("Cannot drive a negative amount of KMs: " + kmAmount);

        if (getFuelAmount() < 1)
            throw new IllegalStateException("The car has no fuel.");

//        if ((kmAmount % 50) != 0)

        return 0;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
