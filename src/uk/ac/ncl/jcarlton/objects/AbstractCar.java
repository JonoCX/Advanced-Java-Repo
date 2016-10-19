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
 * @see uk.ac.ncl.jcarlton.objects.Car
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
        // the registration of car cannot be null
        if (registration == null)
            throw new IllegalArgumentException("Registration cannot be null.");
        // the capacity has to be either 49 (small) or 60 (large)
        if (tankCap != 49 && tankCap != 60)
            throw new IllegalArgumentException("Tank capacity is incorrect, has to be either 49 (small) or 60 (large)");

        this.registrationNumber = registration;
        this.tankCapacity = tankCap;
        this.currentFuelLevel = tankCapacity; // all cars start as full
        this.rented = false; // all cars start as not being rented.
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
        // cannot add a negative amount of fuel to the car.
        if (fuelAmount < 0)
            throw new IllegalArgumentException("Cannot add a negative amount of fuel: " + fuelAmount);

        // cannot add fuel to a full tank
        if (isTankFull())
            return 0;

        // check if there is an overspill (adding more than capacity)
        int overSpillCheck = currentFuelLevel + fuelAmount;
        if (overSpillCheck > tankCapacity) {
            int overspill = overSpillCheck - tankCapacity;

            // remove the extra amount
            int amountAdd = fuelAmount - overspill;

            // essentially fill the tank to its capacity.
            currentFuelLevel += amountAdd;

            // return the actual amount added.
            return amountAdd;
        }

        // else there is no overspill, increment the fuel level
        currentFuelLevel += fuelAmount;
        return fuelAmount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int useFuel(int fuelAmount) {
        // cannot use a negative amount of fuel
        if (fuelAmount < 0)
            throw new IllegalArgumentException("Cannot use a negative amount of fuel: " + fuelAmount);

        // check if there has been an over-usage (using more than the capacity)
        int overUsed = currentFuelLevel - fuelAmount;
        if (overUsed < 0) {
            // record the previous level of fuel - before reduction
            int previousFuelLevel = currentFuelLevel;

            // set to zero
            currentFuelLevel = 0;

            // need to fill the tank up from empty.
            return previousFuelLevel;
        }

        // record previous level of fuel
        int previous = currentFuelLevel;

        // reduce the current fuel level by the amount used
        currentFuelLevel = currentFuelLevel - fuelAmount;

        // previous sub current gives the amount used.
        return previous - currentFuelLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRented(boolean rent) {
        if (isTankFull())
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
