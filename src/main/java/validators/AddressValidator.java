package validators;

import customer.Address;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressValidator {
    public static boolean validateCity(String address) {
        if (address == null) {
            System.out.println("Miasto nie może być puste");
            return false;
        }
        if (address.length() == 0) {
            System.out.println("Miasto nie może być puste");
            return false;
        }
        if (address.trim().length() == 0) {
            System.out.println("Miasto nie może być puste");
            return false;
        }
        String pattern = "^[a-zA-Z ]*$";
        Pattern p = Pattern.compile(pattern);
        Matcher m  = p.matcher(address);
        return m.matches();
    }
}
