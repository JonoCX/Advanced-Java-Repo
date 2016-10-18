package uk.ac.ncl.jcarlton.objects;

/**
 * <h1>Car</h1>
 *
 * The Car interface.
 *
 * It defines the methods and properties
 * that a car should have.
 *
 * @author Jonathan Carlton
 */
public interface Car {

    /**
     * Get the registration of the car object.
     *
     * @return The registration.
     * @see uk.ac.ncl.jcarlton.objects.Registration
     */
    Registration getRegistration();

    /**
     * Get the capacity, in whole litres, of the
     * cars fuel tank.
     *
     * @return  the maximum capacity of the fuel
     *          tank.
     */
    int getFuelCapacity();

    /**
     * Get the amount of fuel, in whole litres, currently
     * in the cars fuel tank.
     *
     * @return  the amount of fuel remaining in the tank.
     */
    int getFuelAmount();

    /**
     * Indicates whether the cars fuel tank
     * is full or not.
     *
     * @return  true, if its full, else false.
     */
    boolean isTankFull();

    /**
     * Add the given amount of fuel to the fuel tank
     * (up to the capacity of the fuel tank).
     *
     * @param fuelAmount the amount of fuel to add to
     *                   the fuel tank.
     * @return           how much fuel was added.
     * @throws IllegalArgumentException thrown if the parameter is
     * a negative value.
     */
    int addFuel(int fuelAmount);

    /**
     * Drive the car for a given number of kilometres.
     *
     * @param kmAmount  the amount of kilometres to
     *                  travel.
     * @return          the amount of fuel that was
     *                  consumed during the travel.
     */
    int drive(int kmAmount);

    /**
     * The ability to use a given amount of fuel in the
     * car. To be used in the implementation of the
     * <code>drive(int kmAmount)</code> method.
     *
     * @param fuelAmount the amount of fuel to
     *                   consume.
     * @return the amount that was actually
     * consumed.
     * @see #drive(int)
     */
    int useFuel(int fuelAmount);

    /**
     *
     * @param rent
     */
    void setRented(boolean rent);

    boolean isRented();
}
