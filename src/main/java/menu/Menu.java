package menu;

import library.Library;

import java.io.IOException;
import java.util.Scanner;

import static menu.LibraryMenuUtils.showMenu;

public class Menu {
//    Library library = Library.getInstance();
    //    LibraryMenu libraryMenu = new LibraryMenu();
//    CatalogueMenu catalogueMenu = new CatalogueMenu();
//    CustomerMenu customerMenu = new CustomerMenu();
//    private final Scanner scanner = new Scanner(System.in);

    public static void readAndExecute() throws IOException {
        Library library = Library.getInstance();
        Scanner scanner = new Scanner(System.in);
        showMenu();
        int input = scanner.nextInt();
        if (input > 4) {
            System.out.println("Nie ma takiej pozycji w menu!!!");
        }
        boolean end = true;
        while (end) {
            switch (input) {
                case 1:
                    LibraryMenu.library();
                    break;
                case 2:
                    CatalogueMenu.catalogue();
                    break;
                case 3:
                    CustomerMenu.customer();
                    break;
                case 4:
                    library.saveData("catalogue.txt", "customers.txt", "rentals.txt");
                    System.exit(0);
                    break;
                default:
                    readAndExecute();
            }
        }
    }
}
