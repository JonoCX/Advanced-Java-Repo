package uk.ac.ncl.jcarlton.app;

import uk.ac.ncl.jcarlton.objects.Car;
import uk.ac.ncl.jcarlton.objects.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jonathan Carlton
 *         17/10/2016
 */
public class RentalManagement {

    private Map<Person, Car> rentedCars;

    public RentalManagement() {
        rentedCars = new HashMap<>();
    }

    public int availableCars(Car type) {
        return 0;
    }

    public Map<Person, Car> getRentedCars() {
        return rentedCars;
    }

    public Car getCar(Person person) {
        return null;
    }



    // if (car instanceof Large) etc...
}
