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
