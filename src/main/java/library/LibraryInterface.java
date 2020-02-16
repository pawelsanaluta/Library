package library;

import book.Book;
import book.Category;
import book.Condition;
import customer.Address;
import customer.Approvals;
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

    //robert
    Customer createAndAddCustomer(String firstName, String lastName, String pesel, String email, String phoneNumber, Address address);
    Customer searchCustomerByPesel(String pesel);
    List<Customer> searchCustomerByName(String firstName, String lastName);
    void editCustomer(Customer customer);
    void removeCustomer(String pesel);
    //Pawel

    void rentBook(String id, String pesel);
    String showCustomerRentals(String pesel);
    void bookBook(String id);
    void returnBook(String id);

    void saveData();
    void saveCustomers(String filename);
    void saveCatalogue(String filename);
    void saveRentals(String filename);

    void readData();
    void readCustomers(String filename);
    void readCatalogue(String filename);
    void readRentals(String filename);

}
