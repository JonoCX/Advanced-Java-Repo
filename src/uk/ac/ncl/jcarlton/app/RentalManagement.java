package uk.ac.ncl.jcarlton.app;

import uk.ac.ncl.jcarlton.objects.*;
import uk.ac.ncl.jcarlton.util.RegistrationGenerator;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * <h1>Car Rental Management</h1>
 *
 * This is the class that the Car Rental Company will
 * use to manage their car rentals - essentially the
 * front-end to the entire project.
 *
 * @author Jonathan Carlton
 */
public final class RentalManagement {

    // all the cars that the company has and if they are rented.
    private Map<Car, Person> rentedCars;

    /**
     *  Object constructor.
     */
    public RentalManagement() {
        rentedCars = populateMap();
    }

    /**
     * Given the class type, count how many cars are
     * available to be rented.
     *
     * @param type  either {@code SmallCar} or {@code LargeCar}, if
     *              a class of another type is passed then the method
     *              will just return -1.
     * @return the count of either {@code SmallCar} or {@code LargeCar} or
     *              -1 if the class type is anything else.
     */
    public int availableCars(Class<?> type) {
        int count = 0;
        if (type == LargeCar.class) { // if its a large car
            for (Map.Entry<Car, Person> m : rentedCars.entrySet()) {
                if (m.getValue() == null && m.getKey() instanceof LargeCar)
                    count++;
            }
            return count;
        } else if (type == SmallCar.class) { // else its a small car
            for (Map.Entry<Car, Person> m : rentedCars.entrySet()) {
                if (m.getValue() == null && m.getKey() instanceof SmallCar)
                    count++;
            }
            return count;
        } else { // else its of neither class type
            return -1;
        }

    }

    /**
     * Get a collection containing all the Car's that are
     * currently rented.
     *
     * @return {@code List<Car>} of all rented Car's
     */
    public List<Car> getRentedCars() {
        List<Car> rented = new ArrayList<>();
        for (Car car : rentedCars.keySet()) {
            if (car.isRented())
                rented.add(car);
        }
        return rented;
    }

    /**
     * Given a {@code Person} fetch the car that they
     * have rented from the rental company.
     *
     * Note: Can return {@code null} if the {@code Car}
     * doesn't exist.
     *
     * @param person    the {@code Person} who has rented a car.
     * @return the {@code Car} which they have rented.
     */
    public Car getCar(Person person) {
        if (person == null)
            throw new IllegalArgumentException("Cannot be a null Person.");

        if (!(rentedCars.containsValue(person))) throw new IllegalArgumentException("Person has not rented a Car");

        Car rentedCar = null;
        for (Map.Entry<Car, Person> m : rentedCars.entrySet()) {
            if (m.getValue() != null && m.getValue().equals(person)) {
                rentedCar = m.getKey();
                break; // break, one-to-one mapping
            }
        }
        return rentedCar;
    }

    /**
     * The functionality to issue a {@code Car} to a {@code Person}, given their
     * driving license and the {@code Car} in which you would like to
     * rent to them.
     * <p>
     * This will return false (the {@code Car} isn't able to be issued) in
     * a number of cases:
     * <ul><li>The license doesn't belong to the {@code Person} being passed</li>
     * <li>The {@code Person} is already renting a {@code Car}</li>
     * <li>The {@code Person} doesn't hold a full driving license</li>
     * <li>The tank (of the {@code Car} that is to be rented) isn't full</li>
     * <li>If they aren't older enough to rent the {@code Car} (Large = 25+ and Small = 20+)</li>
     * <li>The {@code Person} hasn't held their license for long enough (Large = 5 years+
     * and Small = 2 years+)</li></ul>
     * </p>
     * <p>
     * Note: There is an assumption that the person using this method
     * will input Cars that do exist in the rental system.
     * </p>
     *
     * @param person  the person who wants to rent a car
     * @param license the driving license of the person
     * @param car     the car that the person wants to rent
     *                large or small
     * @return if they have been success in renting the
     * car, true or false (see above)
     * @throws IllegalArgumentException thrown when one of the parameters
     *                                  passed is a null object.
     * @see uk.ac.ncl.jcarlton.objects.Person
     * @see uk.ac.ncl.jcarlton.objects.DrivingLicense
     * @see uk.ac.ncl.jcarlton.objects.Car
     * @see uk.ac.ncl.jcarlton.objects.SmallCar
     * @see uk.ac.ncl.jcarlton.objects.LargeCar
     */
    public boolean issueCar(Person person, DrivingLicense license, Car car) {
        // if there is a car of that type available
        // if the car has a full tank of petrol at the start.

        // none of the parameters passed can be null.
        if (person == null)
            throw new IllegalArgumentException("Person cannot be null.");
        if (license == null)
            throw new IllegalArgumentException("License cannot be null.");
        if (car == null)
            throw new IllegalArgumentException("Car cannot be null.");


        // the licence belongs to this person.
        if (!(license.getOwner().equals(person)))
            return false;

        // already renting a car.
        if (rentedCars.containsValue(person))
            return false;

        // doesn't have a full license
        if (!(person.getLicense().isFullLicense()))
            return false;

        // if the tank isn't full
        if (!(car.isTankFull()))
            return false;

        // calculate persons age.
        LocalDate now = LocalDate.now();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(person.getDateOfBirth());
        LocalDate ageDate = LocalDate.of(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        int age = Period.between(ageDate, now).getYears();

        // if they want a small car
        if (car instanceof SmallCar) {
            // they are older than 20
            if (age >= 20) {
                calendar.setTime(license.getSecondComponent());
                LocalDate heldDate = LocalDate.of(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                // held their license for a least 1 year
                if ((1 <= Period.between(heldDate, now).getYears())) {
                    Car issuedCar = null;
                    // find the required car
                    for (Car c : rentedCars.keySet()) {
                        if (c instanceof SmallCar && !c.isRented() && c.getRegistration().equals(car.getRegistration())) {
                            issuedCar = c;
                            break;
                        }
                    }
                    // if it exists
                    if (issuedCar != null) {
                        issuedCar.setRented(true);
                        rentedCars.put(issuedCar, person);
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        // if they want a large car
        if (car instanceof LargeCar) {
            // if they are least 25 years old
            if (age >= 25) {
                calendar.setTime(license.getSecondComponent());
                LocalDate heldDate = LocalDate.of(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                // if they've held their license for at least 5 years.
                if ((5 <= Period.between(heldDate, now).getYears())) {
                    Car issuedCar = null;
                    // find the requested car
                    for (Car c : rentedCars.keySet()) {
                        if (c instanceof LargeCar && !c.isRented() && c.getRegistration().equals(car.getRegistration())) {
                            issuedCar = c;
                            break;
                        }
                    }
                    // if it exists
                    if (issuedCar != null) {
                        issuedCar.setRented(true);
                        rentedCars.put(issuedCar, person);
                        return true;
                    }
                    return false;
                }
            }
        }

        return false;
    }

    /**
     * The functionality to terminate a rental agreement
     * between the rental company and a given person.
     *
     * @param person the person who wants to terminate
     *               their rental contract.
     * @return the amount of fuel required to fill
     * the car's tank or -1 if the person
     * attempts to terminate a non-existent
     * contract.
     * @throws IllegalArgumentException thrown when the parameter
     *                                  passed to the method is a null value.
     */
    public int terminateRental(Person person) {
        if (person == null)
            throw new IllegalArgumentException("Person cannot be null.");

        for (Iterator<Map.Entry<Car, Person>> iterator = rentedCars.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Car, Person> mapEntry = iterator.next();
            // if the person is found
            if (person.equals(mapEntry.getValue())) {
                iterator.remove(); // remove from map
                mapEntry.getKey().setRented(false); // set rented to false

                // return required amount of fuel to fill the car
                return (mapEntry.getKey().getFuelCapacity() - mapEntry.getKey().getFuelAmount());
            }
        }

        // non-existent rental agreement
        return -1;
    }

    /**
     * Populate the map with the rental company's cars.
     *
     * @return the map containing the cars.
     */
    private Map<Car, Person> populateMap() {
        Map<Car, Person> map = new HashMap<>();
        List<Registration> registrations = RegistrationGenerator.registrations();

        // generate large cars
        for (int i = 0; i < 10; i++) {
            map.put(new LargeCar(registrations.get(i)), null);
        }

        // generate small cars
        for (int i = 10; i < 30; i++) {
            map.put(new SmallCar(registrations.get(i)), null);
        }

        return map;
    }

}
