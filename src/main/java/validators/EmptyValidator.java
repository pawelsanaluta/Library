package validators;

public class EmptyValidator {
    public static boolean validator(String toValid) {
        if (toValid == null) {
            return false;
        }
        if (toValid.length() == 0) {
            return false;
        }
        if (toValid.trim().length() == 0) {
            return false;
        }
        return true;
    }
}
