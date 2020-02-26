package menu;

import book.Book;
import book.Category;
import book.Condition;
import com.sun.source.tree.IfTree;
import customer.Address;
import customer.Approvals;
import customer.Customer;
import library.Library;
import validators.EmptyValidator;
import validators.EnumValidator;
import validators.PeselValidator;

import java.io.IOException;
import java.util.*;

import static menu.LibraryMenuUtils.*;

public class LibraryMenu {
    //    CustomerMenu customerMenu = new CustomerMenu();
//    Menu menu = new Menu();
//    private final Scanner scanner = new Scanner(System.in);


//    public void readAndExecute() throws IOException {
//        showMenu();
//        int input = scanner.nextInt();
//        if (input > 4) {
//            System.out.println("Nie ma takiej pozycji w menu!!!");
//        }
//        boolean end = true;
//        while (end) {
//            switch (input) {
//                case 1:
//                    library();
//                    break;
//                case 2:
////                    catalogue();
//                    break;
//                case 3:
////                    customerMenu.customer();
//                    break;
//                case 4:
//                    library.saveData("catalogue.txt", "customers.txt", "rentals.txt");
//                    System.exit(0);
//                    end = false;
//                    break;
//                default:
//                    readAndExecute();
//            }
//        }
//    }

    static void library() throws IOException {
        Library library = Library.getInstance();
        Scanner scanner = new Scanner(System.in);
        showLibraryMenu();
        int input = scanner.nextInt();
        boolean end = true;
        while (end) {
            String finish;
            String pesel;
            switch (input) {
                case 1:
                    System.out.println(library.showCatalogue());
                    menuHandler();
                    break;
                case 2:
                    Library.showCustomers(library.getCustomers());
                    menuHandler();
                    break;
                case 3:
                    List<Book> list = library.listAvailableBooks();
                    for (Book b : list) {
                        System.out.println(b.toString());
                    }
                    menuHandler();
                    break;
                case 4:
                    System.out.println(library.showAllRentals());
                    menuHandler();
                    break;
                case 5:
                    Map<Book, Customer> map = library.mapDeadlineComing();
                    for (Map.Entry<Book, Customer> entry : map.entrySet()) {
                        System.out.print(entry.getKey().getTitle() + " " + entry.getKey().getAuthor());
                        System.out.println(entry.getValue().getFirstName() + " " + entry.getValue().getLastName());
                    }
                    menuHandler();
                    break;
                case 6:
                    Map<Book, Customer> map1 = library.mapDeadlineExceeded();
                    for (Map.Entry<Book, Customer> entry : map1.entrySet()) {
                        System.out.print(entry.getKey().getTitle() + " " + entry.getKey().getAuthor());
                        System.out.println(entry.getValue().getFirstName() + " " + entry.getValue().getLastName());
                    }

                    menuHandler();
                    break;
                case 7:
                    searchingBooks();
                    break;
                case 8:
                    System.out.println("Podaj id książki: ");
                    String id = scanner.next();
                    System.out.println("Podaj PESEL czytelnika");
                    pesel = scanner.next();
                    library.rentBook(id, pesel);
                    System.out.println("Wypożyczono książkę: " + library.getBookByID(id) + " klientowi: " + library.searchCustomerByPesel(pesel));
                    break;
                case 9:
                    System.out.println("Podaj PESEL czytelnika: ");
                    pesel = scanner.next();
                    library.showCustomerRentals(pesel);
                    break;
                case 0:
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
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                System.out.println("Podaj ID książki: ");
                String bookID = scanner.next();
                library.getBookByID(bookID);
                break;
            case 2:
                System.out.println("Podaj słowo kluczowe: ");
                String keyWord = scanner.next();
                library.searchByKeyword(keyWord);
                break;
            case 3:
                System.out.print("Dostępne kategorie książek: ");
                Category.showBookCategories();
                System.out.println("Podaj kategorię: ");
                String category = scanner.next().toUpperCase().trim();
                if (EnumValidator.enumValidate(category)) {
                    library.searchByCategory(Category.valueOf(category));
                } else {
                    System.out.println("Podaj kategorię ponownie");
                    Category.showBookCategories();
                }
                break;
            case 4:
                library();
                showLibraryMenu();
                input = scanner.nextInt();

                break;
        }
    }

//    void catalogue() throws IOException {
//        showCatalogueMenu();
//        int input = scanner.nextInt();
//        switch (input) {
//            case 1:
//                System.out.println("Podaj tytuł książki: ");
//                String title = scanner.next();
//                System.out.println("Podaj autora książki");
//                String author = scanner.next();
//                System.out.println("Podaj rok wydania książki w formacie yyyy");
//                int released = scanner.nextInt();
//                System.out.println("Podaj kategorię książki: ");
//                System.out.println("Dostępne kategorie: ");
//                Category.showBookCategories();
//                Category c = null;
//                String category = scanner.next().toUpperCase().trim();
//                if (EnumValidator.enumValidate(category)) {
//                    c = Category.valueOf(category);
//                } else {
//                    System.out.println("Podaj kategorię ponownie");
//                    Category.showBookCategories();
//                }
//                System.out.println("Podaj stan książki - wybierz:  1. Dobry, 2. Sredni, 3. Zły");
//                Condition bookCondition = null;
//                int condition = scanner.nextInt();
//                if (condition != 1 && condition != 2 && condition != 3) {
//                    System.out.println("Błędny stan książki");
//
//                }
//                if (condition == 1) {
//                    bookCondition = Condition.GOOD;
//                }
//                if (condition == 2) {
//                    bookCondition = Condition.MEDIUM;
//                }
//                if (condition == 3) {
//                    bookCondition = Condition.BAD;
//                }
//                System.out.println("Podaj liczbę stron: ");
//                int pages = scanner.nextInt();
//                System.out.println("Podaj wydawcę książki: ");
//                String publisher = scanner.next();
//                library.addBook(library.createBook(title, author, released, c, bookCondition, pages, publisher));
//                break;
//
//            case 2:
//                System.out.println("Podaj ID książki do usunięcia: ");
//                String id = scanner.next();
//                Book book = library.getBookByID(id);
//                if (book == null) {
//                    break;
//                }
//                System.out.println("Czy jesteś pewien, że chcesz usunąć książkę: " + library.getBookByID(id) + "y/n");
//                String y_n = scanner.next();
//                if (y_n.equals("y")) {
//                    System.out.println("Usunięto książkę: " + library.getBookByID(id));
//                    library.removeBook(id);
//                }
//                if (y_n.equals("n")) {
//                    showCatalogueMenu();
//                }
//                break;
//            case 3:
//                if (library.getCatalogue().size() == 0) {
//                    System.out.println("Katalog książek jest pusty");
//                } else {
//                    System.out.println(library.showCatalogue());
//                }
//                break;
//            case 4:
//                readAndExecute();
//                break;
//        }
//
//    }

//    void customer() throws IOException {
//        showCustomerMenu();
//        Customer customer;
//        int profil = scanner.nextInt();
//        String firstName;
//        String lastName;
//        String pesel;
//        String email;
//        String phoneNumber;
//        String city;
//        String street;
//        String addressNumber;
//        String zipCode;
//        switch (profil) {
//            case 1:
//                System.out.println("Podaj imię: ");
//                firstName = scanner.next();
//                System.out.println("Podaj nazwisko: ");
//                lastName = scanner.next();
//                System.out.println("Podaj PESEL: ");
//                pesel = scanner.next();
//                System.out.println("Podaj adres email: ");
//                email = scanner.next();
//                System.out.println("Podaj telefon: ");
//                phoneNumber = scanner.next();
//                System.out.println("Podaj miasto: ");
//                city = scanner.next();
//                System.out.println("Podaj ulicę: ");
//                street = scanner.next();
//                System.out.println("Podaj numer domu: ");
//                addressNumber = scanner.next();
//                System.out.println("Podaj kod pocztowy: ");
//                zipCode = scanner.next();
//                Address address = new Address(street, city, addressNumber, zipCode);
//                library.createAndAddCustomer(firstName
//                        , lastName, pesel, email, phoneNumber
//                        , address);
//                break;
//
//            case 2:
//                editCustomerData();
//                editCustomerAddress();
//                break;
//            case 3:
//                System.out.println("Podaj PESEL czytelnika: ");
//                pesel = scanner.next();
//                library.searchCustomerByPesel(pesel).setApprovals(Approvals.MARKETING, false);
//                break;
//
//            case 4:
//                System.out.println("Podaj PESEL czytelnika do usunięcia: ");
//                pesel = scanner.next();
//                System.out.println("Czy na pewno chcesz usunąć czytelnika: "
//                        + library.searchCustomerByPesel(pesel).getFirstName()
//                        + " " + library.searchCustomerByPesel(pesel).getLastName()
//                        + " ? y/n");
//                String yes = scanner.next();
//                if (yes.equals("y")) {
//                    library.removeCustomer(pesel);
//                    System.out.println("Usunięto czytelnika");
//                }
//                break;
//
//            case 5:
//                showCustomerMenuSearchCustomer();
//                int searchCustomer = scanner.nextInt();
//                if (searchCustomer == 1) {
//                    System.out.println("Podaj numer PESEL czytelnika");
//                    pesel = scanner.next();
//                    library.searchCustomerByPesel(pesel);
//                }
//                if (searchCustomer == 2) {
//                    System.out.println("Podaj imię i nazwisko czytelnika");
//                    String firstAndLastName = scanner.next();
//                    if (!firstAndLastName.contains(" ")) {
//                        System.out.println("Błędne dane wejściowe, podaj imię i nazwisko czytelnika");
//                    }
//                    String[] afterDivision = firstAndLastName.split(" ");
//                    firstName = afterDivision[0];
//                    lastName = afterDivision[1];
//                    library.searchCustomerByName(firstName, lastName);
//                }
//                break;
//            case 6:
//                Library.showCustomers(library.getCustomers());
//            case 7:
//                readAndExecute();
//                break;
//        }
//
//    }

//    private void editCustomerAddress() throws IOException {
//        boolean end = true;
//        while (end) {
//            showCustomerMenuEditAddress();
//            String pesel;
//            Customer customer;
//            int input = scanner.nextInt();
//            switch (input) {
//                case 1:
//                    System.out.println("Podaj PESEL czytelnika do zmiany danych: ");
//                    pesel = scanner.next();
//                    customer = library.searchCustomerByPesel(pesel);
//                    System.out.println("Podaj nową nazwę ulicy: ");
//                    String street = scanner.next();
//                    customer.getAddress().setStreet(street);
//                    break;
//                case 2:
//                    System.out.println("Podaj PESEL czytelnika do zmiany danych: ");
//                    pesel = scanner.next();
//                    customer = library.searchCustomerByPesel(pesel);
//                    System.out.println("Podaj nową nazwę miejscowości: ");
//                    String city = scanner.next();
//                    customer.getAddress().setCity(city);
//                    break;
//                case 3:
//                    System.out.println("Podaj PESEL czytelnika do zmiany danych: ");
//                    pesel = scanner.next();
//                    customer = library.searchCustomerByPesel(pesel);
//                    System.out.println("Podaj nowy numer domu: ");
//                    String addressNumebr = scanner.next();
//                    customer.getAddress().setAddressNumber(addressNumebr);
//                    break;
//                case 4:
//                    System.out.println("Podaj PESEL czytelnika do zmiany danych: ");
//                    pesel = scanner.next();
//                    customer = library.searchCustomerByPesel(pesel);
//                    System.out.println("Podaj nowy kod pocztowy: ");
//                    String zipCode = scanner.next();
//                    customer.getAddress().setStreet(zipCode);
//                    break;
//                case 5:
//                    readAndExecute();
//                    end = false;
//            }
//        }
//    }

//    private void editCustomerData() throws IOException {
//        boolean end = true;
//        while (end) {
//            showCustomerMenuEditData();
//            int input = scanner.nextInt();
//
//            String pesel;
//            switch (input) {
//                case 1:
//                    System.out.println("Podaj PESEL czytelnika do zmiany danych: ");
//                    pesel = scanner.next();
//                    System.out.println("Podaj nowe imie: ");
//                    String firstName = scanner.next();
//                    library.editCustomerFirstName(pesel, firstName);
//                    break;
//                case 2:
//                    System.out.println("Podaj PESEL czytelnika do zmiany danych: ");
//                    pesel = scanner.next();
//                    System.out.println("Podaj nowe nazwisko: ");
//                    String lastName = scanner.next();
//                    library.editCustomerLastName(pesel, lastName);
//                    break;
//                case 3:
//                    System.out.println("Podaj PESEL czytelnika do zmiany danych: ");
//                    pesel = scanner.next();
//                    System.out.println("Podaj email: ");
//                    String email = scanner.next();
//                    library.editCustomerEmail(pesel, email);
//                    break;
//                case 4:
//                    System.out.println("Podaj PESEL czytelnika do zmiany danych: ");
//                    pesel = scanner.next();
//                    System.out.println("Podaj  nowy numer telefonu: ");
//                    String phoneNumber = scanner.next();
//                    library.editCustomerPhoneNumber(pesel, phoneNumber);
//
//                    break;
//                case 5:
//                    editCustomerAddress();
//                case 6:
//                    showCustomerMenu();
//                    scanner.nextInt();
//                    end = false;
//            }
//        }
//
//    }

}
