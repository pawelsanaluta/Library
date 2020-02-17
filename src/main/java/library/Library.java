package library;

import book.Book;
import book.Category;
import book.Condition;
import customer.Address;
import customer.Customer;
import lombok.Getter;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Library implements LibraryInterface {

    private String name;
    private final static List<Book> CATALOGUE = new ArrayList<>();
    private final static List<Customer> CUSTOMERS = new ArrayList<>();
    private final static Map<Book, Customer> RENTALS = new HashMap<>();
    public Library(String name) {
        this.name = name;
    }

    public static List<Book> getCatalogue() {
        return CATALOGUE;
    }

    public static List<Customer> getCustomers() {
        return CUSTOMERS;
    }

    public static Map<Book, Customer> getRentals() {
        return RENTALS;
    }

    public static void showCustomers(List<Customer> customerList){
        for (Customer c :customerList) {
            System.out.println(c.getFirstName() + " " + c.getLastName() + " Pesel: " + c.getPesel());
            System.out.println("Dane kontaktowe:");
            System.out.print(String.format("Numer telefonu: %-25s",c.getPhoneNumber()));
            System.out.print(String.format("Adres email: %-25s", c.getEmail()));
            System.out.println(String.format(" %-25s", c.getAddress().toString()));
            System.out.println("Zgody: ");
            System.out.println(String.format("%-20s",c.getApprovals().toString()));
        }
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
    public Customer createAndAddCustomer(String firstName, String lastName, String pesel, String email, String phoneNumber, Address address) {
        long count = Library.getCustomers().stream().filter(e -> e.getPesel().equals(pesel)).count();
        if(count == 0) {
            System.out.println("Klient dodany do bazy");
            Customer customer = new Customer(firstName, lastName, pesel, email, phoneNumber, address);
            CUSTOMERS.add(customer);
            return customer;
        } else {
            System.out.println("Klient istnieje już w bazie");
            return null;
        }
    }

    @Override
    public Customer searchCustomerByPesel(String pesel) {
        Optional<Customer> customerOptional = CUSTOMERS.stream().filter(e -> e.getPesel().equals(pesel)).findFirst();
        if(customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            System.out.println("Brak takiego numeru pesel w bazie danych");
            return null;
        }
    }

    @Override
    public List<Customer> searchCustomerByName(String firstName, String lastName) {
        List<Customer> customers = CUSTOMERS.stream().filter(e -> e.getFirstName()
                .contains(firstName) && e.getLastName().contains(lastName))
                .collect(Collectors.toList());
        if(customers.size() != 0) {
            return customers;
        } else {
            System.out.println("Brak takiego klienta w bazie danych");
            return null;
        }
    }

    @Override
    public Customer editCustomer(Customer customer) {
        Customer edited = searchCustomerByPesel(customer.getPesel());
        if(CUSTOMERS.contains(edited)) {
            edited.setFirstName(customer.getFirstName());
            edited.setLastName(customer.getLastName());
            edited.setAddress(customer.getAddress());
            edited.setPhoneNumber(customer.getPhoneNumber());
            edited.setEmail(customer.getEmail());
        } else {
            System.out.println("Brak takiego klienta w bazie danych");
        }
        return edited;
    }

    @Override
    public void removeCustomer(String pesel) {
        Optional<Customer> customerOptional = CUSTOMERS.stream().filter(e-> e.getPesel().equals(pesel)).findFirst();
        customerOptional.ifPresent(CUSTOMERS::remove);
        if(customerOptional.isEmpty()) {
            System.out.println("Brak klienta o podanym numerze pesel");
        }
    }

    @Override
    public void rentBook(String id, String pesel) {
        Book book = getBookByID(id);
        long count = RENTALS.entrySet().stream().filter(e -> e.getValue().getPesel().equals(pesel)).count();
        if(count >= 3) {
            System.out.println("Osiągnięto limit wypożyczonych książek");
        } else {
            if(!RENTALS.containsKey(book)) {
                RENTALS.put(book, searchCustomerByPesel(pesel));
                book.setReturnDeadLine(LocalDate.now().plusDays(14));
            } else {
                System.out.println("Książka jest wypożyczona\n"
                        + "Termin zwrotu dla tej książki to " + book.getReturnDeadLine());
            }
        }
    }

    @Override
    public String showCustomerRentals(String pesel) {
        System.out.println(searchCustomerByPesel(pesel).toString());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Book, Customer> entry : RENTALS.entrySet()) {
            if(entry.getValue().getPesel().equals(pesel)) {
               sb.append(entry.getKey().toString());
            }
        }
        return sb.toString();
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
