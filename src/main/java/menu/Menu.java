package menu;

import library.Library;

import java.io.IOException;
import java.util.Scanner;

import static menu.LibraryMenuUtils.showMenu;

public class Menu {
    Library library = Library.getInstance();
    LibraryMenu libraryMenu = new LibraryMenu();
    private final Scanner scanner = new Scanner(System.in);
    public void readAndExecute() throws IOException {
        showMenu();
        int input = scanner.nextInt();
        if (input > 4) {
            System.out.println("Nie ma takiej pozycji w menu!!!");
        }
        boolean end = true;
        while (end) {
            switch (input) {
                case 1:
                    libraryMenu.library();
//                    library();
                    break;
                case 2:
//                    catalogue();
                    break;
                case 3:
//                    customer();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:

            }

        }

    }
}
