import book.Category;
import customer.Address;
import customer.Customer;
import jdk.management.jfr.FlightRecorderMXBean;
import library.Library;
import menu.LibraryMenu;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        LibraryMenu menu = new LibraryMenu();
        //library.readData();
        menu.readAndExecute();
    }
}
