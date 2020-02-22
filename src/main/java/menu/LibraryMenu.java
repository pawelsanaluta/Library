package menu;

import book.Book;
import book.Category;
import book.Condition;
import com.sun.source.tree.IfTree;
import library.Library;
import validators.EnumValidator;
import validators.PeselValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static menu.LibraryMenuUtils.*;

public class LibraryMenu {
    Library library = Library.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public void readAndExecute() {
        showMenu();
        int input = scanner.nextInt();
        if (input > 4) {
            System.out.println("Nie ma takiej pozycji w menu!!!");
        }
        boolean end = true;
        while (end) {
            switch (input) {
                case 1:
                    library();
                    break;
                case 2:
                    catalogue();
                    break;
                case 3:
                    customer();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:

            }

        }

    }

    private void library() {
        showLibraryMenu();
        String finish;
        String pesel;
        int input = scanner.nextInt();
        boolean end = true;
        while (end) {
            switch (input) {
                case 1:
                    library.showCatalogue();
                    System.out.println("Wciśnij [enter] aby wrócić do menu");
                    finish = scanner.next();
                    if (finish.equals("")){
                        showLibraryMenu();
                    }
                    break;
                case 2:
                    Library.showCustomers(library.getCustomers());
                    System.out.println("Wciśnij [enter] aby wrócić do menu");
                    finish = scanner.next();
                    if (finish.equals("")){
                        showLibraryMenu();
                    }
                    break;
                case 3:
                    library.listAvailableBooks();
                    System.out.println("Wciśnij [enter] aby wrócić do menu");
                    finish = scanner.next();
                    if (finish.equals("")){
                        showLibraryMenu();
                    }
                    break;
                case 4:
                    library.showAllRentals();
                    System.out.println("Wciśnij [enter] aby wrócić do menu");
                    finish = scanner.next();
                    if (finish.equals("")){
                        showLibraryMenu();
                    }
                    break;
                case 5:
                    library.mapDeadlineComing();
                    System.out.println("Wciśnij [enter] aby wrócić do menu");
                    finish = scanner.next();
                    if (finish.equals("")){
                        showLibraryMenu();
                    }
                    break;
                case 6:
                    library.mapDeadlineExceeded();
                    System.out.println("Wciśnij [enter] aby wrócić do menu");
                    finish = scanner.next();
                    if (finish.equals("")){
                        showLibraryMenu();
                    }
                    break;
                case 7:
                    searchingBooks();
                    break;
                case 8:
                    System.out.println("Podaj id książki: ");
                    String id = scanner.next();
                    System.out.println("Podaj PESEL czytelnika");
                    pesel = scanner.next();
                    if (PeselValidator.validate(pesel)){
                        library.rentBook(id,pesel);
                    }
                    System.out.println("Wypożyczono książkę: " + library.getBookByID(id) + " klientowi: " + library.searchCustomerByPesel(pesel));
                    break;
                case 9:
                    System.out.println("Podaj PESEL czytelnika: ");
                    pesel = scanner.next();
                    if (PeselValidator.validate(pesel)){
                        library.showCustomerRentals(pesel);
                    }
                    break;
                case 0:
                    showMenu();

            }
        }
    }

    public void searchingBooks() {
        showLibraryMenuSearchBook();
        int searchingMethod = scanner.nextInt();
        switch (searchingMethod) {
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
                if (EnumValidator.enumValidate(category)){
                    library.searchByCategory(Category.valueOf(category));
                }else{
                    System.out.println("Podaj kategorię ponownie");
                    Category.showBookCategories();
                }
                break;
            case 4:
                showLibraryMenu();
                break;
        }
    }

    private void catalogue() {
        showCatalogueMenu();
        int input = scanner.nextInt();
        switch(input){
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
                if (EnumValidator.enumValidate(category)){
                    c = Category.valueOf(category);
                }else{
                    System.out.println("Podaj kategorię ponownie");
                    Category.showBookCategories();
                }
                System.out.println("Podaj stan książki - wybierz:  1. Dobry, 2. Sredni, 3. Zły");
                Condition bookCondition = null;
                int condition = scanner.nextInt();
                if (condition!=1 && condition !=2 && condition!=3){
                    System.out.println("Błędny stan książki");

                }
                if (condition==1){
                    bookCondition = Condition.GOOD;
                }
                if (condition==2){
                    bookCondition = Condition.MEDIUM;
                }
                if (condition==3){
                    bookCondition = Condition.BAD;
                }
                System.out.println("Podaj liczbę stron: ");
                int pages = scanner.nextInt();
                System.out.println("Podaj wydawcę książki: ");
                String publisher = scanner.next();
                library.addBook(library.createBook(title, author, released, c , bookCondition, pages, publisher));
                break;

            case 2:
                System.out.println("Podaj ID książki do usunięcia: ");
                String id = scanner.next();
                System.out.println("Czy jesteś pewien, że chcesz usunąć książkę: " + library.getBookByID(id) + "y/n");
                String y_n = scanner.next();
                if (y_n.equals("y")){
                    System.out.println("Usunięto książkę: " + library.getBookByID(id));
                    library.removeBook(id);
                }
                if (y_n.equals("n")){
                    showCatalogueMenu();
                }
                break;
            case 3:
                library.showCatalogue();
                break;
            case 4:
                showLibraryMenu();
                break;
        }

    }

    private void customer() {
        showCustomerMenu();
        int profil = scanner.nextInt();
        switch (profil){
            case 6:
                showCustomerMenuSearchCustomer();
                int searchCustomer = scanner.nextInt();
                if (searchCustomer==1){
                    System.out.println("Podaj numer PESEL czytelnika");
                    String pesel = scanner.next();
                    library.searchCustomerByPesel(pesel);
                }
                if (searchCustomer==2){
                    System.out.println("Podaj imię i nazwisko czytelnika");
                    String firstAndLastName = scanner.next();
                    if (!firstAndLastName.contains(" ")){
                        System.out.println("Błędne dane wejściowe, podaj imię i nazwisko czytelnika");
                    }
                    String[] afterDivision = firstAndLastName.split(" ");
                    String firstName = afterDivision[0];
                    String lastName = afterDivision[1];
                    library.searchCustomerByName(firstName, lastName);
                }
        }
    }

}
