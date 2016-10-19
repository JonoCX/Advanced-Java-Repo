package uk.ac.ncl.jcarlton.util;

import uk.ac.ncl.jcarlton.objects.Registration;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>RegistrationGenerator</h1>
 *
 * Used to generate the registrations of the
 * cars that the company is managing.
 *
 * @see uk.ac.ncl.jcarlton.objects.Registration
 *
 * @author Jonathan Carlton
 */
public final class RegistrationGenerator {

    /**
     * Get all of the registrations of the cars that the
     * company manages.
     *
     * @return A {@code List<Registration>} of registrations
     */
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
}
