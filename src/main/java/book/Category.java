package book;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<Category> getCategories() {
        return categories;
    }

    public static void listCategories() {
        Collections.addAll(categories, values());
    }

    public static void showBookCategories() {
        for (Category c : values()) {
            System.out.print(c.toString().toLowerCase() + " ");
        }
        System.out.println("\n");
    }
}
