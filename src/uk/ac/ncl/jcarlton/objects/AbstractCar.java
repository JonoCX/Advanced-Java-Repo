package uk.ac.ncl.jcarlton.objects;

/**
 * @author Jonathan Carlton
 */
public abstract class AbstractCar implements Car {

    private Registration registrationNumber;

    AbstractCar() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Registration getRegistration() {
        return null;
    }

    @Override
    public int getFuelCapacity() {
        return 0;
    }

    @Override
    public int getFuelAmount() {
        return 0;
    }

    @Override
    public boolean isTankFull() {
        return false;
    }

    @Override
    public int addFuel(int fuelAmount) {
        return 0;
    }

    @Override
    public int drive(int kmAmount) {
        return 0;
    }
}
