package book;

public enum Category {
    FANTASY,
    SCIENTIFIC,
    NOVEL,
    ROMANCE,
    CRIMINAL,
    PRESS,
    THRILLER
;

    public static void showBookCategories() {
        for (Category c : values()) {
            System.out.print(c.toString().toLowerCase() + " ");
        }
        System.out.println("\n");
    }
}
