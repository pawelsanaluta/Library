package menu;

import book.Book;
import book.Category;
import library.Library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static menu.LibraryMenuUtils.*;

public class LibraryMenu {
    Library library;
    private final Scanner scanner = new Scanner(System.in);

    public void readAndExecute(){
        showMenu();
        int input = scanner.nextInt();
        if (input > 4){
            System.out.println("Nie ma takiej pozycji w menu!!!");
        }
        boolean end = true;
        while (end) {
            switch (input){
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
        boolean end =true;
        while (end){
            switch(input){
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
        switch (searchingMethod){
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
                System.out.println("Dostępne kategorie książek:");

                System.out.println("Podaj kategorię: ");

        }
    }

    private void catalogue() {
        showCatalogueMenu();
    }
    private void customer() {
        showCustomerMenu();
    }

}
