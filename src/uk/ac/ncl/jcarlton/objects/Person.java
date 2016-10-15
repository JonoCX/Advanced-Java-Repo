package uk.ac.ncl.jcarlton.objects;

import java.util.Date;

/**
 * <h1>Person</h1>
 *
 * Represents an immutable person.
 *
 * @author Jonathan Carlton
 */
public final class Person {

    // final member variables
    private final String firstName;
    private final String lastName;
    private final Date dateOfBirth;

    /**
     * Create a person from a first name, last name and a date of birth.
     *
     * @param firstName     The first name of the person.
     * @param lastName      The last name of the person.
     * @param dateOfBirth   The date of birth of the person.
     * @throws IllegalArgumentException     Thrown if a null or empty variable is passed
     *                                      as part of the construction.
     */
    public Person(String firstName, String lastName, Date dateOfBirth) {
        if (firstName == null || firstName.trim().isEmpty())
            throw new IllegalArgumentException("First name cannot be null or an empty string.");
        if (lastName == null || lastName.trim().isEmpty())
            throw new IllegalArgumentException("Last name cannot be null or an empty string.");
        if (dateOfBirth == null)
            throw new IllegalArgumentException("Date of Birth cannot be null");

        // trim the parameters to remove whitespace
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.dateOfBirth = new Date(dateOfBirth.getTime());
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return getFirstName().equals(person.getFirstName()) &&
                getLastName().equals(person.getLastName()) &&
                getDateOfBirth().equals(person.getDateOfBirth());
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int h = getFirstName().hashCode();
        h = 31 * h + getLastName().hashCode();
        h = 31 * h + getDateOfBirth().hashCode();
        return h;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[" + "Name='" + firstName + " " + lastName + "', dob=" + dateOfBirth + "]";
    }

    /**
     * Get the first name of the Person object.
     * @return  The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name of the Person object.
     * @return  The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the date of birth of the Person object.
     * @return  The date of birth.
     */
    public Date getDateOfBirth() {
        // able to safely use the clone() method when using Date
        return (Date) dateOfBirth.clone();
    }

}
