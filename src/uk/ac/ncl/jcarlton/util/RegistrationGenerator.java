package uk.ac.ncl.jcarlton.util;

import uk.ac.ncl.jcarlton.objects.Registration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Jonathan Carlton
 *         17/10/2016
 */
public final class RegistrationGenerator {

    public static List<Registration> registrations() {
        List<Registration> registrations = new ArrayList<>();
        registrations.add(Registration.getInstance("FP61", "RVG"));
        registrations.add(Registration.getInstance("YN56", "QPI"));
        registrations.add(Registration.getInstance("TP00", "QQJ"));
        registrations.add(Registration.getInstance("TM02", "RQW"));
        registrations.add(Registration.getInstance("DI00", "LRB"));
        registrations.add(Registration.getInstance("FP12", "SBJ"));
        registrations.add(Registration.getInstance("SS61", "RJK"));
        registrations.add(Registration.getInstance("IA52", "XCY"));
        registrations.add(Registration.getInstance("OD96", "ZEC"));
        registrations.add(Registration.getInstance("HK11", "KMG"));
        registrations.add(Registration.getInstance("NE49", "WQH"));
        registrations.add(Registration.getInstance("PJ53", "TEV"));
        registrations.add(Registration.getInstance("XL92", "HNQ"));
        registrations.add(Registration.getInstance("BM84", "CRS"));
        registrations.add(Registration.getInstance("HN33", "BMR"));
        registrations.add(Registration.getInstance("SE61", "RLU"));
        registrations.add(Registration.getInstance("AA78", "OGG"));
        registrations.add(Registration.getInstance("KA16", "UAR"));
        registrations.add(Registration.getInstance("QU02", "MWE"));
        registrations.add(Registration.getInstance("ZW80", "YGG"));
        registrations.add(Registration.getInstance("WN38", "AZM"));
        registrations.add(Registration.getInstance("KE34", "YED"));
        registrations.add(Registration.getInstance("VD35", "VXB"));
        registrations.add(Registration.getInstance("KD76", "ORG"));
        registrations.add(Registration.getInstance("SP01", "HOM"));
        registrations.add(Registration.getInstance("JD46", "XLK"));
        registrations.add(Registration.getInstance("RI87", "XGK"));
        registrations.add(Registration.getInstance("QI85", "GJY"));
        registrations.add(Registration.getInstance("NE32", "ASW"));
        registrations.add(Registration.getInstance("DZ61", "LFK"));
        return registrations;
    }

    public static List<Registration> generate() {
        List<Registration> registrations = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String twoLetters = generateLetters(2);
            String digits = generateDigits();
            String threeLetters = generateLetters(3);
            registrations.add(Registration.getInstance(
                    twoLetters + digits, threeLetters
            ));
        }
        return registrations;
    }

    private static String generateDigits() {
        Random random = new Random();
        int first = random.nextInt(10);
        int second = random.nextInt(10);
        return String.valueOf(first) + String.valueOf(second);
    }

    private static String generateLetters(int length) {
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        if (length == 2) {
            for (int i = 0; i < length; i++)
                builder.append(alphabet.charAt(random.nextInt(alphabet.length())));
            return builder.toString();
        } else {
            for (int i = 0; i < length; i++)
                builder.append(alphabet.charAt(random.nextInt(alphabet.length())));
            return builder.toString();
        }
    }
}
