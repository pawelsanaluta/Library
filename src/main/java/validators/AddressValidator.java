package validators;

import customer.Address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        List<Character> letters = Arrays.asList('q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z',
                'x','c','v','b','n','m',' ','ą','ć','ś','ę','ł','ń','ż','ź','ó');
//        char[] chars = "qwertyuiopasdfghjklzxcvbnm ąćśęłńżźó".toCharArray();
        address = address.toLowerCase();
        char[] addressChars = address.toCharArray();
        for (int i = 0; i < addressChars.length; i++) {
            if (!letters.contains(addressChars[i])){
                System.out.println("Adres zawiera niedozwolone znaki");
                return false;
            }

        }
        return true;
    }
}
