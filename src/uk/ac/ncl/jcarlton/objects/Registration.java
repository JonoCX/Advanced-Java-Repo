package uk.ac.ncl.jcarlton.objects;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Registration</h1>
 *
 * The representation of a car registration.
 *
 * This class enforces uniqueness, to ensure that
 * no two registrations can be the same.
 *
 * @author Jonathan Carlton
 */
public final class Registration {

    // state member variables
    private final String firstComponent;
    private final String secondComponent;

    // store all the registrations, to ensure uniqueness.
    private static final Map<String, Registration> REGISTRATION_MAP = new HashMap<>();

    /**
     * Private object constructor.
     *
     * Create a registration from two components, the first being
     * a String that is 2 letters followed by 2 digits and the
     * second being another String that is 3 letters.
     *
     * "NG57 HXE".
     *
     * @param first     first component of the registration (String).
     * @param second     second component of the registration (String).
     * @throws IllegalArgumentException     thrown when the lengths of the
     * variables are incorrect or, if they are null or, if they are empty, or
     * if they don't meet the regex patterns.
     */
    private Registration(String first, String second) {
        // regex to ensure that the first component matches the two letters two digits pattern.
        if (!(first.matches("[a-zA-Z]{2}[0-9]{2}")))
            throw new IllegalArgumentException("The first component has to be 2 letters followed by 2 digits.");
        if (first == null || first.isEmpty() || first.trim().isEmpty())
            throw new IllegalArgumentException("First component cannot be null or an empty string");

        // regex to ensure that the second component is three letters.
        if (!(second.matches("[a-zA-Z]{3}")))
            throw new IllegalArgumentException("The second component can only contain 3 letters");
        if (second == null || second.isEmpty() || second.trim().isEmpty())
            throw new IllegalArgumentException("Third component cannot be null or an empty string");

        // trim (to remove leading/trailing whitespace) the strings and make upperCase.
        this.firstComponent = first.trim().toUpperCase();
        this.secondComponent = second.trim().toUpperCase();
    }

    /**
     * Static factory method to either create a new registration or
     * fetch a previously created registration.
     *
     * @param first     the first component of the registration.
     * @param second    the second component of the registration.
     * @return          a registration object, either new or one
     *                  that was previously created.
     * @throws IllegalArgumentException thrown when the object is being created.
     */
    public static Registration getInstance(String first, String second) {
        final String check = first.toUpperCase() + " " + second.toUpperCase();

        // if the map doesn't contain the registration key.
        if (!(REGISTRATION_MAP.containsKey(check)))
            // create a new registration and add it to the map.
            REGISTRATION_MAP.put(check, new Registration(first, second));

        // then return the newly created or previously stored registration.
        return REGISTRATION_MAP.get(check);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return firstComponent + " " + secondComponent;
    }

    /**
     * Get the first component of the Registration object.
     * @return  the first component.
     */
    public String getFirstComponent() {
        return firstComponent;
    }


    /**
     * Get the third component of the Registration object.
     * @return  the third component.
     */
    public String getSecondComponent() {
        return secondComponent;
    }
}
