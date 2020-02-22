package library;

import book.Book;
import book.Category;
import book.Condition;
import customer.Address;
import customer.Customer;

import java.util.List;
import java.util.Map;

public interface LibraryInterface {

    Book createBook(String title, String author, int released, Category category, Condition condition, int pages, String publisher);

    void addBook(Book book);

    Book getBookByID(String id);

    void removeBook(String id);

    List<Book> searchByKeyword(String keyword);

    List<Book> searchByCategory(Category category);

    List<Book> listAvailableBooks();

    Customer createAndAddCustomer(String firstName, String lastName, String pesel, String email, String phoneNumber, Address address);

    Customer searchCustomerByPesel(String pesel);

    List<Customer> searchCustomerByName(String firstName, String lastName);

    void editCustomerFirstName(String pesel, String firstName);

    void editCustomerLastName(String pesel, String lastName);

    void editCustomerEmail(String pesel, String email);

    void editCustomerPhoneNumber(String pesel, String phoneNumber);

    void editCustomerAddress(String pesel, Address address);

    void removeCustomer(String pesel);

    void rentBook(String id, String pesel);

    String showCustomerRentals(String pesel);

    void returnBook(String id);

    String showAllRentals();

    Map<Book, Customer> mapDeadlineComing();

    Map<Book, Customer> mapDeadlineExceeded();

    void saveData();

    void saveCustomers();

    void saveCatalogue();

    void saveRentals();

    void readData();

    void readCustomers();

    void readCatalogue();

    void readRentals();

}
