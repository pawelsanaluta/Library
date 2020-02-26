package menu;

import library.Library;

import java.io.IOException;
import java.util.Scanner;

import static menu.LibraryMenuUtils.showMenu;

public class Menu {

    public static void readAndExecute() throws IOException {
        Library library = Library.getInstance();
        Scanner scanner = new Scanner(System.in);
        showMenu();
        String input = scanner.next();
            switch (input) {
                case "1":
                    LibraryMenu.library();
                    break;
                case "2":
                    CatalogueMenu.catalogue();
                    break;
                case "3":
                    CustomerMenu.customer();
                    break;
                case "0":
                    library.saveData("catalogue.txt", "customers.txt", "rentals.txt");
                    System.exit(0);
                    break;
                default:
                    readAndExecute();
            }
    }
}
