package uk.ac.ncl.jcarlton.objects;

/**
 * <h1>AbstractCar</h1>
 *
 * Partial implementation of the {@code Car} interface.
 * All of the common behaviour, shared between cars, has
 * been implemented here.
 *
 * To add further specialist functionality, extend this
 * class.
 *
 * @author Jonathan Carlton
 */
public abstract class AbstractCar implements Car {

    // state member variables
    private final Registration registrationNumber; // unique to the car
    private final int tankCapacity;
    private int currentFuelLevel;

    private boolean rented;

    /**
     * Package-private object constructor, used by the
     * classes which extend this class.
     * @param registration  the registration plate of the car.
     * @param tankCap       the fuel capacity of the car.
     */
    AbstractCar(Registration registration, int tankCap) {
        if (registration == null)
            throw new IllegalArgumentException("Registration cannot be null.");
        if (tankCap != 49 && tankCap != 60)
            throw new IllegalArgumentException("Tank capacity is incorrect, has to be either 49 (small) or 60 (large)");

        this.registrationNumber = registration;
        this.tankCapacity = tankCap;
        this.currentFuelLevel = tankCapacity; // all cars start as full
        this.rented = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Registration getRegistration() {
        return registrationNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFuelCapacity() {
        return tankCapacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFuelAmount() {
        return currentFuelLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTankFull() {
        return currentFuelLevel == tankCapacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int addFuel(int fuelAmount) {
        if (fuelAmount < 0)
            throw new IllegalArgumentException("Cannot add a negative amount of fuel: " + fuelAmount);

        // cannot add fuel to a full tank
        if (isTankFull())
            return 0;

        int overSpillCheck = currentFuelLevel + fuelAmount;
        if (overSpillCheck > tankCapacity) {
            int overspill = overSpillCheck - tankCapacity;
            int amountAdd = fuelAmount - overspill;
            currentFuelLevel += amountAdd;
            return amountAdd;
        }

        currentFuelLevel += fuelAmount;
        return fuelAmount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int useFuel(int fuelAmount) {
        if (fuelAmount < 0)
            throw new IllegalArgumentException("Cannot use a negative amount of fuel: " + fuelAmount);

        int overUsed = currentFuelLevel - fuelAmount;
        if (overUsed < 0) {
            int previousFuelLevel = currentFuelLevel;
            currentFuelLevel = 0;
            return previousFuelLevel;
        }

        int previous = currentFuelLevel;
        currentFuelLevel = currentFuelLevel - fuelAmount;
        return previous - currentFuelLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRented(boolean rent) {
        this.rented = rent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRented() {
        return rented;
    }

    /**
     * The equality of a {@code Car} is based on whether
     * or their registrations are the same.
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Registration) return false;

        AbstractCar that = (AbstractCar) o;

        return registrationNumber.equals(that.registrationNumber);

    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return registrationNumber.hashCode();
    }
}
