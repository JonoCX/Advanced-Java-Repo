package uk.ac.ncl.jcarlton.objects;

import java.util.*;

/**
 * @author Jonathan Carlton
 */
public class DrivingLicense {

    // state member variables
    private final String firstComponent;
    private final Date secondComponent;
    private final String thirdComponent;

    // information about the license
    private final boolean fullLicense;
    private final Person owner;

    // store all the driving license
    private static final Map<Person, DrivingLicense> LICENSE_MAP = new HashMap<>();

    /**
     * Private object constructor.
     * <p>
     * Create a {@code DrivingLicense} using a {@code Person}, a {@code Date},
     * and a {@code String}. Also indicate whether it is a full license or not.
     *
     * @param person      the {@code Person} whose license it is.
     * @param second      the {@code Date} of which the license was issued.
     * @param third       the {@code String} unique serial number.
     * @param fullLicense if it's a full license or not.
     * @see uk.ac.ncl.jcarlton.objects.Person
     */
    private DrivingLicense(Person person, Date second, String third, boolean fullLicense) {
        this.firstComponent = processName(person);
        this.secondComponent = second;
        this.thirdComponent = third;
        this.fullLicense = fullLicense;
        this.owner = person;
    }

    /**
     * Static factory method to either create a new {@code DrivingLicense} or fetch
     * a previously created one.
     *
     * @param person      the {@code Person} whose license it is.
     * @param second      the {@code Date} of which the license was issued.
     * @param fullLicense if it's a full license or not.
     * @return the stored or created object.
     */
    public static DrivingLicense getInstance(Person person, Date second, boolean fullLicense) {
        // ensure that the Person isn't null
        if (person == null)
            throw new IllegalArgumentException("The Person cannot be a null object");

        // ensure that the date isn't null.
        if (second == null)
            throw new IllegalArgumentException("Date cannot be a null object");

        // if the map doesn't contain the person, then create a new license
        if (!(LICENSE_MAP.containsKey(person)))
            LICENSE_MAP.put(person, new DrivingLicense(person, second, generateSerial(), fullLicense));

        // then return the stored license
        return LICENSE_MAP.get(person);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(secondComponent);
        return firstComponent + "-" + calendar.get(Calendar.YEAR) + "-" + thirdComponent + "-" + fullLicense;
    }

    /**
     * Get the first component.
     * @return the first component.
     */
    public String getFirstComponent() {
        return firstComponent;
    }

    /**
     * Get the second component.
     * @return the second component.
     */
    public Date getSecondComponent() {
        return secondComponent;
    }

    /**
     * Get the third component.
     * @return the third component.
     */
    public String getThirdComponent() {
        return thirdComponent;
    }

    /**
     * Fetch if the license is full or not.
     * @return true or false.
     */
    public boolean isFullLicense() {
        return fullLicense;
    }

    /**
     * Get the owner of the license.
     * @return the {@code Person}.
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * Process the name of a {@code Person} to get the first
     * letter of their first and last name.
     * @param person    the {@code Person} to process.
     * @return the concatenation of the first and
     *                  last name letters.
     */
    private static String processName(Person person) {
        String firstInitial = person.getFirstName().substring(0, 1);
        String secondInitial = person.getLastName().substring(0, 1);
        String concat = firstInitial + secondInitial;
        return concat.toUpperCase();
    }

    /**
     * Generate the random serial for the license.
     * @return the serial as a {@code String}.
     */
    private static String generateSerial() {
        Random random = new Random();
        int first = random.nextInt(10);
        int second = random.nextInt(10);
        return String.valueOf(first) + String.valueOf(second);
    }
}
