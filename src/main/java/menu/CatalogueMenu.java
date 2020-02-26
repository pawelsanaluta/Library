package menu;

import book.Book;
import book.Category;
import book.Condition;
import library.Library;
import validators.EnumValidator;

import java.io.IOException;
import java.util.Scanner;

import static menu.LibraryMenuUtils.showCatalogueMenu;

public class CatalogueMenu {
//    LibraryMenu libraryMenu = new LibraryMenu();
//    Menu menu = new Menu();
    //    private final Scanner scanner = new Scanner(System.in);
//    Library library = Library.getInstance();

    static void catalogue() throws IOException {
        Library library = Library.getInstance();
        showCatalogueMenu();
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                System.out.println("Podaj tytuł książki: ");
                String title = scanner.next();
                System.out.println("Podaj autora książki");
                String author = scanner.next();
                System.out.println("Podaj rok wydania książki w formacie yyyy");
                int released = scanner.nextInt();
                System.out.println("Podaj kategorię książki: ");
                System.out.println("Dostępne kategorie: ");
                Category.showBookCategories();
                Category c = null;
                String category = scanner.next().toUpperCase().trim();
                if (EnumValidator.enumValidate(category)) {
                    c = Category.valueOf(category);
                } else {
                    System.out.println("Podaj kategorię ponownie");
                    Category.showBookCategories();
                }
                System.out.println("Podaj stan książki - wybierz:  1. Dobry, 2. Sredni, 3. Zły");
                Condition bookCondition = null;
                int condition = scanner.nextInt();
                if (condition != 1 && condition != 2 && condition != 3) {
                    System.out.println("Błędny stan książki");

                }
                if (condition == 1) {
                    bookCondition = Condition.GOOD;
                }
                if (condition == 2) {
                    bookCondition = Condition.MEDIUM;
                }
                if (condition == 3) {
                    bookCondition = Condition.BAD;
                }
                System.out.println("Podaj liczbę stron: ");
                int pages = scanner.nextInt();
                System.out.println("Podaj wydawcę książki: ");
                String publisher = scanner.next();
                library.addBook(library.createBook(title, author, released, c, bookCondition, pages, publisher));
                break;

            case 2:
                System.out.println("Podaj ID książki do usunięcia: ");
                String id = scanner.next();
                Book book = library.getBookByID(id);
                if (book == null) {
                    break;
                }
                System.out.println("Czy jesteś pewien, że chcesz usunąć książkę: " + library.getBookByID(id) + "y/n");
                String y_n = scanner.next();
                if (y_n.equals("y")) {
                    System.out.println("Usunięto książkę: " + library.getBookByID(id));
                    library.removeBook(id);
                }
                if (y_n.equals("n")) {
                    showCatalogueMenu();
                }
                break;
            case 3:
                if (library.getCatalogue().size() == 0) {
                    System.out.println("Katalog książek jest pusty");
                } else {
                    System.out.println(library.showCatalogue());
                }
                break;
            case 4:
                Menu.readAndExecute();
                break;
        }

    }

}
