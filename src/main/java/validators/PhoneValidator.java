package validators;

import java.util.Arrays;
import java.util.List;

public class PhoneValidator {
    public static boolean validate(String phoneNumber) {
        if (phoneNumber == null) {
            System.out.println("Błędny numer telefonu");
            return false;
        }
        if (phoneNumber.length() == 0) {
            System.out.println("Błędny numer telefonu");
            return false;
        }
        if (phoneNumber.trim().length() == 0) {
            System.out.println("Błędny numer telefonu");
            return false;
        }
        if (phoneNumber.length() != 9){
            System.out.println("Błędny numer - za dużo cyfr");
            return false;
        }
        List<Character> digits = Arrays.asList('1','2','3','4','5','6','7','8','9','0');
        char[] numberAsTab = phoneNumber.toCharArray();
        for (int i = 0; i < 9; i++) {
            if (!digits.contains(numberAsTab[i])){
                return false;
            }
        }
        return true;
    }
}
