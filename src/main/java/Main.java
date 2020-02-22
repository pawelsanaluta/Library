import book.Category;
import customer.Address;
import customer.Customer;
import library.Library;
import menu.LibraryMenu;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        LibraryMenu menu = new LibraryMenu();
        menu.readAndExecute();
    }
}
