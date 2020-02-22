package validators;

import book.Category;

public class EnumValidator {
    public static boolean enumValidate(String toValidate) {
        if (toValidate == null) {
            System.out.println("Kategoria książek nie może byc null");
            return false;
        }
        if (toValidate.length() == 0) {
            System.out.println("Kategoria książek nie może byc pusta");
            return false;
        }
        if (toValidate.trim().length() == 0) {
            System.out.println("Kategoria książek nie może zawierać samych spacji");
            return false;
        }
        if (toValidate.contains(Category.getCategories())){

        }
//    }
        return true;
    }
}
