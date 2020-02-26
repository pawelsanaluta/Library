package menu;

import book.Book;
import book.Category;
import customer.Customer;
import library.Library;
import validators.EnumValidator;

import java.io.IOException;
import java.util.*;

import static menu.LibraryMenuUtils.*;

public class LibraryMenu {

    static void library() throws IOException {
        Library library = Library.getInstance();
        Scanner scanner = new Scanner(System.in);
        showLibraryMenu();
        String input = scanner.next();
        boolean end = true;
        while (end) {
            String pesel;
            switch (input) {
                case "1":
                    System.out.println(library.showCatalogue());
                    menuHandler();
                    break;
                case "2":
                    Library.showCustomers(library.getCustomers());
                    menuHandler();
                    break;
                case "3":
                    List<Book> list = library.listAvailableBooks();
                    for (Book b : list) {
                        System.out.println(b.toString());
                    }
                    menuHandler();
                    break;
                case "4":
                    System.out.println(library.showAllRentals());
                    menuHandler();
                    break;
                case "5":
                    Map<Book, Customer> map = library.mapDeadlineComing();
                    for (Map.Entry<Book, Customer> entry : map.entrySet()) {
                        System.out.print(entry.getKey().getTitle() + " " + entry.getKey().getAuthor());
                        System.out.println(entry.getValue().getFirstName() + " " + entry.getValue().getLastName());
                    }
                    menuHandler();
                    break;
                case "6":
                    Map<Book, Customer> map1 = library.mapDeadlineExceeded();
                    for (Map.Entry<Book, Customer> entry : map1.entrySet()) {
                        System.out.print(entry.getKey().getTitle() + " " + entry.getKey().getAuthor());
                        System.out.println(entry.getValue().getFirstName() + " " + entry.getValue().getLastName());
                    }

                    menuHandler();
                    break;
                case "7":
                    searchingBooks();
                    menuHandler();
                    break;
                case "8":
                    rentABook();
                    menuHandler();
                    break;
                case "9":
                    System.out.println("Podaj PESEL czytelnika: ");
                    pesel = scanner.next();
//                    if (library.showCustomerRentals(pesel)==null && library.getCustomers()==null){
//                        System.out.println("Brak klienów w bazie");
//                    }
                    while (library.showCustomerRentals(pesel)==null){
                        System.out.println("Podaj pesel ponownie");
                        pesel = scanner.next();
                    }
//                    library.showCustomerRentals(pesel);
                    menuHandler();
                    break;
                case "0":
                    Menu.readAndExecute();
                    end = false;
                    break;
                default:
                    System.out.println("Brak takiej pozycji w menu");
                    Menu.readAndExecute();
            }
        }
    }

    static void menuHandler() throws IOException {
        System.out.println("Wciśnij [q] aby wrócić do menu Biblioteka");
        Scanner sc = new Scanner(System.in);
        String finish = sc.nextLine();
        if (finish.equals("q")) {
            LibraryMenu.library();
        }
    }

    public static void searchingBooks() throws IOException {
        Library library = Library.getInstance();
        Scanner scanner = new Scanner(System.in);
        showLibraryMenuSearchBook();
        String input = scanner.next();
        switch (input) {
            case "1":
                System.out.println("Podaj ID książki: ");
                String bookID = scanner.next();
                System.out.println(library.getBookByID(bookID));
                break;
            case "2":
                System.out.println("Podaj słowo kluczowe: ");
                String keyWord = scanner.next();
                System.out.println(library.searchByKeyword(keyWord));
                break;
            case "3":
                System.out.print("Dostępne kategorie książek: ");
                Category.showBookCategories();
                System.out.println("Podaj kategorię: ");
                String category = scanner.next().toUpperCase().trim();
                if (EnumValidator.enumValidate(category)) {
                    System.out.println(library.searchByCategory(Category.valueOf(category)));
                } else {
                    System.out.println("Podaj kategorię ponownie");
                    Category.showBookCategories();
                }
                break;
            case "0":
                library();
                showLibraryMenu();
                break;
        }
    }

    public static void rentABook() throws IOException {
        Library library = Library.getInstance();
        String pesel;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id książki: ");
        String id = scanner.next();
        while (library.getBookByID(id) == null) {
            System.out.println("Błędne id książki, podaj ponownie: ");
            id = scanner.next();
        }
        System.out.println("Wybrano książkę: " + library.getBookByID(id).getAuthor() + " " + library.getBookByID(id).getTitle());
        System.out.println("Podaj PESEL czytelnika");
        pesel = scanner.next();
        while (library.searchCustomerByPesel(pesel) == null) {
            System.out.println("Błędny PESEL czytelnika, podaj ponowinie: ");
            pesel = scanner.next();
        }
        library.rentBook(id, pesel);
        System.out.println("Wypożyczono książkę: " + library.getBookByID(id) + " klientowi: "
                + library.searchCustomerByPesel(pesel).getFirstName() + " "
                + library.searchCustomerByPesel(pesel).getLastName());
    }
}

