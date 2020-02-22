package menu;

import book.Book;
import book.Category;
import com.sun.source.tree.IfTree;
import library.Library;
import validators.EnumValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static menu.LibraryMenuUtils.*;

public class LibraryMenu {
    Library library;
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
        int input = scanner.nextInt();
        boolean end = true;
        while (end) {
            switch (input) {
                case 1:
                    break;
                case 2:
                    Library.showCustomers(library.getCustomers());
                    break;
                case 3:
                    //aktualnie dostępne
                    break;
                case 4:
                    //aktualnie wypożyczone
                    break;
                case 5:
                    //zbliżający sie termin oddania
                    break;
                case 6:
                    //książki po terminie
                    break;
                case 7:
                    searchingBooks();

                case 8:
                case 9:
                case 0:

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
                String category = scanner.next().toUpperCase().trim();
                if (EnumValidator.enumValidate(category)){
                    library.searchByCategory(Category.valueOf(category));
                }else{
                    System.out.println("Podaj kategorię ponownie");
                    Category.showBookCategories();
                }

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
