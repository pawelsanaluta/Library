package book;

import java.util.ArrayList;
import java.util.List;

public enum Category {
    FANTASY,
    SCIENTIFIC,
    NOVEL,
    ROMANCE,
    CRIMINAL,
    PRESS,
    THRILLER
;

    private static List<Category> categories = new ArrayList<>();

    public static List<Category> listCategories() {
        List<Category> list = new ArrayList<>();
        Category[] values = Category.values();
        for (int i = 0; i < values.length; i++) {
            list.add(values[i]);
        }
        return list;
    }

    public static void showBookCategories() {
        for (Category c : values()) {
            System.out.print(c.toString().toLowerCase() + " ");
        }
        System.out.println("\n");
    }
}
