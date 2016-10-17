package uk.ac.ncl.jcarlton.app;

import uk.ac.ncl.jcarlton.objects.*;
import uk.ac.ncl.jcarlton.util.RegistrationGenerator;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * @author Jonathan Carlton
 *         17/10/2016
 */
public final class RentalManagement {

    private Map<Car, Person> rentedCars;

    public RentalManagement() {
        rentedCars = populateMap();
    }

    private Map<Car, Person> populateMap() {
        Map<Car, Person> map = new HashMap<>();
        List<Registration> registrations = RegistrationGenerator.generate();

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

    public List<Car> getRentedCars() {
        List<Car> rented = new ArrayList<>();
        for (Car car : rentedCars.keySet()) {
            if (car.isRented())
                rented.add(car);
        }
        return rented;
    }

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
     *
     * @param person
     * @param license
     * @param car
     * @return
     * @throws IllegalArgumentException thrown when one of the parameters
     * passed is a null object.
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

        if (car instanceof SmallCar) {
            if (age >= 20) {
                calendar.setTime(license.getSecondComponent());
                LocalDate heldDate = LocalDate.of(
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                if ((1 <= Period.between(heldDate, now).getYears())) {
                    rentedCars.put(car, person);
                    return true;
                }
            }
            return false;
        }
        if (car instanceof LargeCar) {
           if (age >= 25) {
               calendar.setTime(license.getSecondComponent());
               LocalDate heldDate = LocalDate.of(
                       calendar.get(Calendar.YEAR),
                       calendar.get(Calendar.MONTH) + 1,
                       calendar.get(Calendar.DAY_OF_MONTH)
               );
               if ((5 <= Period.between(heldDate, now).getYears())) {
                   rentedCars.put(car, person);
                   return true;
               }
           }
        }

        return false;
    }

    /**
     *
     * @param person    the person who wants to terminate
     *                  their rental contract.
     * @return          the amount of fuel required to fill
     *                  the car's tank or -1 if the person
     *                  attempts to terminate a non-existent
     *                  contract.
     * @throws IllegalArgumentException thrown when the parameter
     * passed to the method is a null value.
     */
    public int terminateRental(Person person) {
        if (person == null)
            throw new IllegalArgumentException("Person cannot be null.");

        for (Iterator<Map.Entry<Car, Person>> iterator = rentedCars.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<Car, Person> mapEntry = iterator.next();
            if (person.equals(mapEntry.getValue())) {
                iterator.remove();
                return (mapEntry.getKey().getFuelCapacity() - mapEntry.getKey().getFuelAmount());
            }
        }
        return -1;
    }

}
