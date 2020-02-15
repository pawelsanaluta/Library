package validators;

import java.util.Arrays;
import java.util.List;

public class PeselValidator {
    public static boolean validator(String pesel) {
        if (pesel == null) {
            System.out.println("Pesel nie może byc null!");
            return false;
        }
        if (pesel.length() == 0) {
            System.out.println("Pesel zawiera 0 znaków");
            return false;
        }
        if (pesel.length() != 11) {
            System.out.println("Liczba znaków różna od 11 !!!");
            return false;
        }
        List<Character> chars = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0');
        char[] peselToTab = pesel.toCharArray();
        for (int i = 0; i < peselToTab.length; i++) {
            if (!chars.contains(peselToTab[i])) {
                return false;
            }
        }
        int[] ints = new int[11];
        for (int i = 0; i < 11; i++) {
            ints[i] = Integer.parseInt(String.valueOf(peselToTab[i]));
        }
        int sum = 0;
        sum = ints[0] + ints[1] * 3 + ints[2] * 7 + ints[3] * 9 +
                ints[4] + ints[5] * 3 + ints[6] * 5 + ints[6] * 7 +
                ints[7] * 9 + ints[8] + ints[9] * 3;
        sum = sum % 10;
        sum = 10 - sum;
        sum = sum % 10;
        return sum == ints[10];
    }
}
