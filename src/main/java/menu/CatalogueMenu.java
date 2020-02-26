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

    static void catalogue() throws IOException {
        Library library = Library.getInstance();
        showCatalogueMenu();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        switch (input) {
            case "1":
                System.out.println("Podaj tytuł książki: ");
                String title = scanner.next();
                System.out.println("Podaj autora książki");
                String author = scanner.next();
                System.out.println("Podaj rok wydania książki");
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
                menuHandler();
                break;

            case "2":
                System.out.println("Podaj ID książki do usunięcia: ");
                String id = scanner.next().toUpperCase();
                while (library.getBookByID(id) == null) {
                    System.out.println("Błędne ID książki, podaj ponownie: ");
                    id = scanner.next().toUpperCase();
                }
//                Book book = library.getBookByID(id);
//                if (book == null) {
//                    break;
//                }
                System.out.println("Czy jesteś pewien, że chcesz usunąć książkę: " + library.getBookByID(id) + "y/n");
                String y_n = scanner.next();
                if (y_n.equals("y")) {
                    System.out.println("Usunięto książkę: " + library.getBookByID(id));
                    library.removeBook(id);
                }
                if (y_n.equals("n")) {
                    System.out.println("Książka" + library.getBookByID(id).getAuthor()
                            + " " + library.getBookByID(id).getTitle()
                            + " dalej znajduje się w katalogu");
                    showCatalogueMenu();
                }
                menuHandler();
                break;
            case "3":
                if (library.getCatalogue().size() == 0) {
                    System.out.println("Katalog książek jest pusty");
                } else {
                    System.out.println(library.showCatalogue());
                }
                menuHandler();
                break;
            case "0":
                Menu.readAndExecute();
                break;
        }

    }

    private static void menuHandler() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wciśnij [q] aby powrócić do menu Czytelnik");
        String finish = scanner.nextLine();
        if (finish.equals("q")) {
            CatalogueMenu.catalogue();
        }
    }

}
