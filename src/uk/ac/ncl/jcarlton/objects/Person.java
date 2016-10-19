package uk.ac.ncl.jcarlton.objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private final DrivingLicense license;


    /**
     * Create a person from a first name, last name and a date of birth.
     *
     * @param firstName   The first name of the person.
     * @param lastName    The last name of the person.
     * @param dateOfBirth The date of birth of the person.
     * @param fullLicense if the license is full or not
     * @param licenseIssue  when the license was issued.
     * @throws IllegalArgumentException Thrown if a null or empty variable is passed
     *                                  as part of the construction.
     */
    public Person(String firstName, String lastName, Date dateOfBirth, boolean fullLicense, Date licenseIssue) {
        if (firstName == null || firstName.trim().isEmpty())
            throw new IllegalArgumentException("First name cannot be null or an empty string.");
        if (lastName == null || lastName.trim().isEmpty())
            throw new IllegalArgumentException("Last name cannot be null or an empty string.");
        if (dateOfBirth == null)
            throw new IllegalArgumentException("Date of Birth cannot be null.");
        if (licenseIssue == null)
            throw new IllegalArgumentException("The license issue date cannot be null.");

        // trim the parameters to remove whitespace
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.dateOfBirth = new Date(dateOfBirth.getTime());
        this.license = generateLicense(fullLicense, licenseIssue);
    }

    /**
     * Generate the persons driving license based on the
     * parameters passed to the object constructor.
     *
     * @param full if the license is full or not
     * @param licenseIssue when the license was issued
     * @return the persons driving license
     */
    private DrivingLicense generateLicense(boolean full, Date licenseIssue) {
        return DrivingLicense.getInstance(this, licenseIssue, full);
    }


    /**
     * A Person is determined to be equal when they
     * have the same first and last name, and their
     * date of birth is the same.
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
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return firstName + " " + lastName +
                ":" + format.format(dateOfBirth) +
                ":" + license.toString();
    }

    /**
     * Give a {@code String} create a {@code Person}.
     *
     * @see #toString()
     *
     * @param person    the {@code String} representation of the {@code Person}.
     * @return a {@code Person} object.
     * @throws ParseException   thrown when the date of birth cannot
     * be parsed by {@code SimpleDateFormat}.
     */
    public static Person valueOf(String person) throws ParseException {
        // cannot parse an empty or null string.
        if (person == null || person.isEmpty())
            throw new IllegalArgumentException("Cannot parse empty or null string");

        String[] firstSplit = person.split(" ");
        String[] secondSplit = firstSplit[1].split(":");

        SimpleDateFormat format;
        format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(secondSplit[1]); // throws ParseException.


        String[] thirdSplit = secondSplit[2].split("-");
        format = new SimpleDateFormat("yyyy");
        DrivingLicense license = DrivingLicense.getInstance(
                new Person(
                        firstSplit[0], // first name
                        secondSplit[0], // last name
                        date, // date of birth
                        Boolean.valueOf(thirdSplit[3]), // full license or not
                        format.parse(thirdSplit[1]) // license issue
                ),
                format.parse(thirdSplit[1]),
                Boolean.valueOf(thirdSplit[3]));

        return license.getOwner();
    }

    /**
     * Get the first name of the Person object.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name of the Person object.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the date of birth of the Person object.
     *
     * @return The date of birth.
     */
    public Date getDateOfBirth() {
        // able to safely use the clone() method when using Date
        return (Date) dateOfBirth.clone();
    }

    /**
     * Get the driving license of the Person.
     *
     * @return  The driving license.
     */
    public DrivingLicense getLicense() {
        return license;
    }
}
