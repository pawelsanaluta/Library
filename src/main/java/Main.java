import book.Category;
import customer.Address;
import customer.Customer;
import jdk.management.jfr.FlightRecorderMXBean;
import library.Library;
import menu.LibraryMenu;
import menu.Menu;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        Library library = Library.getInstance();
        library.readData("catalogue.txt", "customers.txt", "rentals.txt");
//        library.readBooksFromTextFile("books.txt");
//        library.readCustomersFromTextFile("cust.txt");
//        Menu menu = new Menu();
        Menu.readAndExecute();
//        LibraryMenu libraryMenu = new LibraryMenu();
//        libraryMenu.readAndExecute();
    }
}
