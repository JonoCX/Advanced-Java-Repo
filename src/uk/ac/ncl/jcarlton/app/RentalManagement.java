package uk.ac.ncl.jcarlton.app;

import uk.ac.ncl.jcarlton.objects.*;
import uk.ac.ncl.jcarlton.util.RegistrationGenerator;

import java.util.*;

/**
 * @author Jonathan Carlton
 *         17/10/2016
 */
public class RentalManagement {

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

    public int availableCars(Car type) {
        int count = 0;
        if (type instanceof LargeCar) { // if its a large car
            for (Map.Entry<Car, Person> m : rentedCars.entrySet()) {
                if (m.getValue() == null) // works as when populating you sent person to null.
                    count++;
            }
            return count;
        } else { // else its a small car
            for (Map.Entry<Car, Person> m : rentedCars.entrySet()) {
                if (m.getValue() == null)
                    count++;
            }
            return count;
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
        if (person == null) throw new IllegalArgumentException("Cannot be a null Person.");

        if (!(rentedCars.containsValue(person))) throw new IllegalArgumentException("Person has not rented a Car");

        Car rentedCar = null;
        for (Map.Entry<Car, Person> m : rentedCars.entrySet()) {
            if (m.getValue().equals(person)) {
                rentedCar = m.getKey();
                break; // break, one-to-one mapping
            }
        }
        return rentedCar;
    }

    public boolean issueCar(Person person, DrivingLicense license, Car car) {
        // if there is a car of that type available
        // if the car has a full tank of petrol at the start.
        if (rentedCars.containsValue(person)) return false;
        return false;

    }

    public int terminateRental(Person person) {
        return 0;
    }



    // if (car instanceof Large) etc...
}
