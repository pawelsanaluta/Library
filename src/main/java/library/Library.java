package library;

import book.Book;
import book.Category;
import book.Condition;
import customer.Address;
import customer.Approvals;
import customer.Customer;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Library implements LibraryInterface {

    private String name;
    private final static List<Book> CATALOGUE = new ArrayList<>();
    private final static List<Customer> CUSTOMERS = new ArrayList<>();
    private final static Map<String, Customer> RENTALS = new HashMap<>();

    public Library(String name) {
        this.name = name;
    }

    public static List<Book> getCatalogue() {
        return CATALOGUE;
    }

    public static List<Customer> getCustomer() {
        return CUSTOMERS;
    }

    public static Map<String, Customer> getRentals() {
        return RENTALS;
    }

    public Book createBook(String title, String author, int released, Category category, Condition condition, int pages, String publisher) {
        String id = Book.createID();
        long count = CATALOGUE.stream().filter(e -> e.getId().equals(id)).count();
        if (count == 0) {
            return new Book(title, author, id, released, category, condition, pages, publisher);
        } else {
            return createBook(title, author, released, category, condition, pages, publisher);
        }
    }

    public void addBook(Book book) {
        long count = CATALOGUE.stream().filter(e -> e.getId().equals(book.getId())).count();
        if(count == 0) {
            System.out.println("Dodano nową książkę");
            CATALOGUE.add(book);
        } else {
            System.out.println("Ta książka jest już w katalogu");
        }
    }

    public Book getBookByID(String id) {
        Optional<Book> book = CATALOGUE.stream().filter(e -> e.getId().equals(id)).findAny();
        if(book.isPresent()) {
            return book.get();
        } else {
            System.out.println("Brak takiej książki w katalogu");
            return null;
        }
    }

    public void removeBook(String id) {
        Book book = getBookByID(id);
        if(book != null) {
            System.out.println("Usunięto książkę " + book.getTitle());
            CATALOGUE.remove(book);
        }
    }

    public List<Book> searchByKeyword(String keyword) {
        if(keyword.length() > 2) {
            return  CATALOGUE.stream().filter(e -> e.getTitle().toLowerCase().contains(keyword.toLowerCase()) || e.getAuthor().toLowerCase().contains(keyword.toLowerCase())
                    || e.getPublisher().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
        } else {
            System.out.println("Słowo kluczowe powinno zawierać minimum 3 znaki");
            return null;
        }
    }

    public List<Book> searchByCategory(Category category) {
        return CATALOGUE.stream().filter(e -> e.getCategory().equals(category)).collect(Collectors.toList());
    }

    @Override
    public Customer createCustomer(String firstName, String lastName, String pesel, String email, String phoneNumber, Map<Approvals, Boolean> approvals, Address address) {
        return null;
    }

    @Override
    public void addCustomer(Customer customer) {

    }

    @Override
    public void removeCustomer(String pesel) {

    }

    @Override
    public void editCustomer(Customer customer) {

    }

    @Override
    public Customer searchCustomerByPesel(String pesel) {
        return null;
    }

    @Override
    public Customer searchCustomerByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public String showCustomerRentals(String pesel) {
        return null;
    }

    @Override
    public void rentBook(String id) {

    }

    @Override
    public void bookBook(String id) {

    }

    @Override
    public void returnBook(String id) {

    }

    @Override
    public void saveData() {

    }

    @Override
    public void saveCustomers(String filename) {

    }

    @Override
    public void saveCatalogue(String filename) {

    }

    @Override
    public void saveRentals(String filename) {

    }

    @Override
    public void readData() {

    }

    @Override
    public void readCustomers(String filename) {

    }

    @Override
    public void readCatalogue(String filename) {

    }

    @Override
    public void readRentals(String filename) {

    }

}
