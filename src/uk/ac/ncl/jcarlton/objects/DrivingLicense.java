package uk.ac.ncl.jcarlton.objects;

import java.util.*;

/**
 * @author Jonathan Carlton
 */
public class DrivingLicense {

    // state member variables
    private final String firstComponent;
    private final Date secondComponent;
    private final int thirdComponent;

    private final boolean fullLicense;

    private static final Map<String, DrivingLicense> LICENSE_MAP = new HashMap<>();

    private DrivingLicense(Person person, Date second, int third, boolean fullLicense) {
        // ensure that the Person isn't null
        if (person == null)
            throw new IllegalArgumentException("The Person cannot be a null object");

        // ensure that the date isn't null.
        if (second == null)
            throw new IllegalArgumentException("Date cannot be a null object");

        this.firstComponent = processName(person);
        this.secondComponent = second;
        this.thirdComponent = third;
        this.fullLicense = fullLicense;
    }

    public static DrivingLicense getInstance(Person person, Date second, boolean fullLicense) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(second);
        final String check = processName(person) + " " + cal.get(Calendar.YEAR);

        if (!(LICENSE_MAP.containsKey(check)))
            LICENSE_MAP.put(check, new DrivingLicense(person, second, generateSerial(), fullLicense));

        return LICENSE_MAP.get(check);
    }

    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(secondComponent);
        return firstComponent + "-" + calendar.get(Calendar.YEAR) + "-" + thirdComponent;
    }

    public String getFirstComponent() {
        return firstComponent;
    }

    public Date getSecondComponent() {
        return secondComponent;
    }

    public int getThirdComponent() {
        return thirdComponent;
    }

    public boolean isFullLicense() {
        return fullLicense;
    }

    private static String processName(Person person) {
        String firstInitial = person.getFirstName().substring(0, 1);
        String secondInitial = person.getLastName().substring(0, 1);
        String concat = firstInitial + secondInitial;
        return concat.toUpperCase();
    }

    private static int generateSerial() {
        Random random = new Random();
        return random.nextInt(100);
    }
}
