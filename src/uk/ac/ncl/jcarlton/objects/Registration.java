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
    private final int secondComponent;
    private final String thirdComponent;

    // store all the registrations, to ensure uniqueness.
    private static final Map<String, Registration> REGISTRATION_MAP = new HashMap<>();

    /**
     * Private object constructor.
     *
     * Create a registration from three components, the first being
     * a String with a length of 2, a integer representing the
     * year the car was produced, and a String with a length of 3.
     *
     * "NG57 HXE".
     *
     * @param first     first component of the registration (String).
     * @param second    second component of the registration (<code>int</code>).
     * @param third     third component of the registration (String).
     * @throws IllegalArgumentException     thrown when the lengths of the
     * variables are incorrect or, if they are null or, if they are empty.
     */
    private Registration(String first, int second, String third) {
        if (first.length() != 2)
            throw new IllegalArgumentException("First component has a length that is not equal to 2");
        if (first == null || first.isEmpty() || first.trim().isEmpty())
            throw new IllegalArgumentException("First component cannot be null or an empty string");

        if (String.valueOf(second).length() != 2)
            throw new IllegalArgumentException("Second component has a length not equal to 2.");

        if (third.length() != 3)
            throw new IllegalArgumentException("Third component has a length that is not equal to 3");
        if (third == null || third.isEmpty() || third.trim().isEmpty())
            throw new IllegalArgumentException("Third component cannot be null or an empty string");

        // trim the strings and make upperCase.
        this.firstComponent = first.trim().toUpperCase();
        this.secondComponent = second;
        this.thirdComponent = third.trim().toUpperCase();
    }

    /**
     * Static factory method to either create a new registration or
     * fetch a previously created registration.
     *
     * @param first     the first component of the registration.
     * @param second    the second component of the registration.
     * @param third     the third component of the registration.
     * @return          a registration object, either new or one
     *                  that was previously created.
     * @throws IllegalArgumentException thrown when the object is being created.
     */
    public static Registration getInstance(String first, int second, String third) {
        final String check = first.toUpperCase() + second + " " + third.toUpperCase();

        // if the map doesn't contain the registration key.
        if (!(REGISTRATION_MAP.containsKey(check)))
            // create a new registration and add it to the map.
            REGISTRATION_MAP.put(check, new Registration(first, second, third));

        // then return the newly created or previously stored registration.
        return REGISTRATION_MAP.get(check);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return firstComponent + secondComponent + " " + thirdComponent;
    }

    /**
     * Get the first component of the Registration object.
     * @return  the first component.
     */
    public String getFirstComponent() {
        return firstComponent;
    }

    /**
     * Get the second component of the Registration object.
     * @return  the second component.
     */
    public int getSecondComponent() {
        return secondComponent;
    }

    /**
     * Get the third component of the Registration object.
     * @return  the third component.
     */
    public String getThirdComponent() {
        return thirdComponent;
    }
}
