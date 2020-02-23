package validators;

import customer.Address;
import org.apache.http.impl.execchain.ServiceUnavailableRetryExec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressValidator {
    public static boolean validateCity(String address) {
        if (address == null) {
            System.out.println("Miasto nie może być null");
            return false;
        }
        if (address.length() == 0) {
            System.out.println("Miasto nie może być puste");
            return false;
        }
        if (address.trim().length() == 0) {
            System.out.println("Miasto nie może zawierać samych spacji");
            return false;
        }
        List<Character> letters
                = Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', ' ', 'ą', 'ć', 'ś', 'ę', 'ł', 'ń', 'ż', 'ź', 'ó');
        address = address.toLowerCase();
        char[] addressChars = address.toCharArray();
        for (int i = 0; i < addressChars.length; i++) {
            if (!letters.contains(addressChars[i])) {
                System.out.println("Miasto zawiera niedozwolone znaki");
                return false;
            }
        }
        return true;
    }

    public static boolean validateStreet(String street) {
        if (street == null) {
            System.out.println("Ulica nie może być null");
            return false;
        }
        if (street.length() == 0) {
            System.out.println("Ulica nie może być pusta");
            return false;
        }
        if (street.trim().length() == 0) {
            System.out.println("Ulica nie może zawierać samych spacji");
            return false;
        }
        List<Character> letters
                = Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z',
                'x', 'c', 'v', 'b', 'n', 'm', ' ', 'ą', 'ć', 'ś', 'ę', 'ł', 'ń', 'ż', 'ź', 'ó', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '\'');
        street = street.toLowerCase();
        char[] addressChars = street.toCharArray();
        for (int i = 0; i < addressChars.length; i++) {
            if (!letters.contains(addressChars[i])) {
                System.out.println("Ulica zawiera niedozwolone znaki");
                return false;
            }
        }
        return true;
    }

    public static boolean validateAddressNumber(String addressNumber) {
        if (addressNumber == null) {
            System.out.println("Numer nie może byc null");
            return false;
        }
        if (addressNumber.length() == 0) {
            System.out.println("Numer nie może być pusty");
            return false;
        }
        if (addressNumber.trim().length() == 0) {
            System.out.println("Numer nie może zawierać samych spacji");
            return false;
        }
        List<Character> houseNumbers = Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z',
                'x', 'c', 'v', 'b', 'n', 'm', ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '\'');
        addressNumber = addressNumber.toLowerCase();
        char[] numberChars = addressNumber.toCharArray();
        for (int i = 0; i < numberChars.length; i++) {
            if (!houseNumbers.contains(numberChars[i])) {
                System.out.println("Numer zawiera niedozwolone znaki");
                return false;
            }
        }
        return true;
    }

    public static boolean validateZipCode(String zipCode) {
        if (zipCode == null) {
            System.out.println("Kod pocztowy nie może być null");
            return false;
        }
        if (zipCode.length() == 0) {
            System.out.println("Kod pocztowy nie może pyć pusty");
            return false;
        }
        if (zipCode.trim().length() == 0) {
            System.out.println("Kod pocztowy nie może zawierać samych spacji");
            return false;
        }
        if (zipCode.length() > 6 || zipCode.length() < 5) {
            System.out.println("Kod pocztowy zawiera nieprawidłową liczbę znaków");
            return false;
        }
        if (zipCode.length() == 6 && !zipCode.contains("-")) {
            System.out.println("Kod pocztowy ma 6 znaków lecz jest nieprawidłowy");
            return false;
        }
        if (zipCode.length() == 6 && zipCode.contains("-") && zipCode.charAt(2) != ('-')) {
            System.out.println("Kod pocztowy ma w złym miejscu \" - \" ");
            return false;
        }
        if (zipCode.length() == 6 && zipCode.contains("-")) {
            List<Character> chars = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-');
            char[] zipCodeToTab = zipCode.toCharArray();
            for (int i = 0; i < zipCodeToTab.length; i++) {
                if (!chars.contains(zipCodeToTab[i])) {
                    System.out.println("Kod pocztowy zawiera nieprawidłowe znaki ale ma dobrą długość");
                    return false;
                }
            }
        }
        if (zipCode.length() == 6 && zipCode.contains("-") && zipCode.charAt(2) == ('-')) {
            List<Character> chars = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-');
            char[] zipCodeToTab = zipCode.toCharArray();
            for (int i = 0; i < zipCodeToTab.length; i++) {
                if (chars.contains(zipCodeToTab[i])) {
                    System.out.println("Kod pocztowy prawidłowy");
                    return true;
                }
            }
        }
        if (zipCode.length() == 5) {
            List<Character> chars = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0');
            char[] zipCodeToTab = zipCode.toCharArray();
            for (int i = 0; i < zipCodeToTab.length; i++) {
                if (!chars.contains(zipCodeToTab[i])) {
                    System.out.println("Kod pocztowy zawiera 5 znaków ale nie są to liczby");
                    return false;
                }
            }
        }
        return true;
    }
}
