package library;

import book.Book;
import book.Category;
import book.Condition;
import customer.Address;
import customer.Customer;
import lombok.Getter;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Library implements LibraryInterface, Serializable {

    private List<Book> catalogue = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private Map<Book, Customer> rentals = new HashMap<>();
    public static Library instance;

    private static final long serialVersionUID = 1L;

    private Library() {
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public List<Book> getCatalogue() {
        return catalogue;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Map<Book, Customer> getRentals() {
        return this.rentals;
    }

    public static void showCustomers(List<Customer> customerList) {
        for (Customer c : customerList) {
            System.out.println(c.getFirstName() + " " + c.getLastName() + " Pesel: " + c.getPesel());
            System.out.println("Dane kontaktowe:");
            System.out.print(String.format("Numer telefonu: %-25s", c.getPhoneNumber()));
            System.out.print(String.format("Adres email: %-25s", c.getEmail()));
            System.out.println(String.format(" %-25s", c.getAddress().toString()));
            System.out.println("Zgody: ");
            System.out.println(String.format("%-20s", c.getApprovals().toString()));
        }
    }

    public void sendEMail(String receiver, String subject, String message) {
        SendEmailSMTP.sendEmail(receiver, subject, message);
    }

    public void sendDeadlineReminder() {
        String subject = "Przypomnienie o terminie zwrotu książki";
        Map<Book, Customer> deadline = mapDeadlineComing();
        for (Map.Entry<Book, Customer> entry : deadline.entrySet()) {
            String message = "Przypominamy o zbliżającym się terminie zwrotu książki "
                    + entry.getKey().getTitle() + " dnia " + entry.getKey().getReturnDeadLine().toString();
            String receiver = entry.getValue().getEmail();
            sendEMail(receiver, subject, message);
        }
    }

    public void sendDeadlineExceededReminder() {
        String subject = "Przypomnienie o przekroczonym terminie zwrotu";
        Map<Book, Customer> exceeded = mapDeadlineExceeded();
        for (Map.Entry<Book, Customer> entry : exceeded.entrySet()) {
            String message = "Informujemy, że minął termin zwrotu książki " + entry.getKey().getTitle()
                    + ". Prosimy o natychmiastowy zwrot ww. książki do biblioteki";
            String receiver = entry.getValue().getEmail();
            sendEMail(receiver, subject, message);
        }
    }

    public Book createBook(String title, String author, int released, Category category, Condition condition, int pages, String publisher) {
        String id = Book.createID();
        long count = this.catalogue.stream().filter(e -> e.getId().equals(id)).count();
        if (count == 0) {
            return new Book(title, author, id, released, category, condition, pages, publisher);
        } else {
            return createBook(title, author, released, category, condition, pages, publisher);
        }
    }

    public void addBook(Book book) {
        long count = this.catalogue.stream().filter(e -> e.getId().equals(book.getId())).count();
        if (count == 0) {
            System.out.println("Dodano nową książkę");
            this.catalogue.add(book);
        } else {
            System.out.println("Ta książka jest już w katalogu");
        }
    }

    public Book getBookByID(String id) {
        Optional<Book> book = this.catalogue.stream().filter(e -> e.getId().equals(id)).findAny();
        if (book.isPresent()) {
            return book.get();
        } else {
            System.out.println("Brak takiej książki w katalogu");
            return null;
        }
    }

    public void removeBook(String id) {
        Book book = getBookByID(id);
        if (book != null) {
            System.out.println("Usunięto książkę " + book.getTitle());
            this.catalogue.remove(book);
        }
    }

    public String showCatalogue() {
        StringBuilder sb = new StringBuilder();
        for (Book book : this.catalogue) {
            sb.append(book.toString());
        }
        return sb.toString();
    }

    public List<Book> searchByKeyword(String keyword) {
        if (keyword.length() > 2) {
            return this.catalogue.stream().filter(e -> e.getTitle().toLowerCase().contains(keyword.toLowerCase()) || e.getAuthor().toLowerCase().contains(keyword.toLowerCase())
                    || e.getPublisher().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
        } else {
            System.out.println("Słowo kluczowe powinno zawierać minimum 3 znaki");
            return null;
        }
    }

    public List<Book> searchByCategory(Category category) {
        return this.catalogue.stream().filter(e -> e.getCategory().equals(category)).collect(Collectors.toList());
    }

    @Override
    public List<Book> listAvailableBooks() {
        List<Book> available = this.catalogue;
        for (Map.Entry<Book, Customer> entry : this.rentals.entrySet()) {
            Book book = entry.getKey();
            available.remove(book);
        }
        return available;
    }

    @Override
    public Customer createAndAddCustomer(String firstName, String lastName, String pesel, String email, String phoneNumber, Address address) {
        long count = getCustomers().stream().filter(e -> e.getPesel().equals(pesel)).count();
        if (count == 0) {
            Customer customer = new Customer(firstName, lastName, pesel, email, phoneNumber, address);
            System.out.println("Klient dodany do bazy");
            this.customers.add(customer);
            return customer;
        } else {
            System.out.println("Klient istnieje już w bazie");
            return null;
        }
    }

    @Override
    public Customer searchCustomerByPesel(String pesel) {
        Optional<Customer> customerOptional = customers.stream().filter(e -> e.getPesel().equals(pesel)).findFirst();
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            System.out.println("Brak takiego klienta w bazie danych");
            return null;
        }
    }

    @Override
    public List<Customer> searchCustomerByName(String firstName, String lastName) {
        List<Customer> customers = this.customers.stream().filter(e -> e.getFirstName()
                .contains(firstName) && e.getLastName().contains(lastName))
                .collect(Collectors.toList());
        if (customers.size() != 0) {
            return customers;
        } else {
            System.out.println("Brak takiego klienta w bazie danych");
            return null;
        }
    }

    @Override
    public void editCustomerFirstName(String pesel, String firstName) {
        Customer edited = searchCustomerByPesel(pesel);
        if (edited != null) {
            edited.setFirstName(firstName);
        }
    }

    @Override
    public void editCustomerLastName(String pesel, String lastName) {
        Customer edited = searchCustomerByPesel(pesel);
        if (edited != null) {
            edited.setLastName(lastName);
        }
    }

    @Override
    public void editCustomerEmail(String pesel, String email) {
        Customer edited = searchCustomerByPesel(pesel);
        if (edited != null) {
            edited.setEmail(email);
        }
    }

    @Override
    public void editCustomerPhoneNumber(String pesel, String phoneNumber) {
        Customer edited = searchCustomerByPesel(pesel);
        if (edited != null) {
            edited.setPhoneNumber(phoneNumber);
        }
    }

    @Override
    public void editCustomerAddress(String pesel, Address address) {
        Customer edited = searchCustomerByPesel(pesel);
        if (edited != null) {
            edited.setAddress(address);
        }
    }

    @Override
    public void removeCustomer(String pesel) {
        Optional<Customer> customerOptional = this.customers.stream().filter(e -> e.getPesel().equals(pesel)).findFirst();
        customerOptional.ifPresent(this.customers::remove);
        if (customerOptional.isEmpty()) {
            System.out.println("Brak klienta o podanym numerze pesel");
        } else {
            System.out.println("Usunięto klienta nr " + customerOptional.get().getPesel());
        }
    }

    @Override
    public void rentBook(String id, String pesel) {
        Book book = getBookByID(id);
        long count = this.rentals.entrySet().stream().filter(e -> e.getValue().getPesel().equals(pesel)).count();
        if (count >= 3) {
            System.out.println("Osiągnięto limit wypożyczonych książek");
        } else {
            if (!this.rentals.containsKey(book)) {
                this.rentals.put(book, searchCustomerByPesel(pesel));
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
        for (Map.Entry<Book, Customer> entry : this.rentals.entrySet()) {
            if (entry.getValue().getPesel().equals(pesel)) {
                sb.append(entry.getKey().toString());
            }
        }
        return sb.toString();
    }

    @Override
    public void returnBook(String id) {
        Book book = getBookByID(id);
        if (this.rentals.containsKey(book)) {
            this.rentals.remove(book);
            System.out.println("Zwrócono książkę \"" + book.getTitle() + "\"");
        } else {
            System.out.println("Brak książki w bazie wypożyczeń");
        }
    }


    @Override
    public String showAllRentals() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Book, Customer> entry : this.rentals.entrySet()) {
            sb.append(entry.getKey().getTitle())
                    .append("; ")
                    .append(entry.getKey().getAuthor())
                    .append("; ID: ")
                    .append(entry.getKey().getId());
            sb.append(" Rented by: ")
                    .append(entry.getValue().getFirstName())
                    .append(" ")
                    .append(entry.getValue().getLastName())
                    .append(" ID: ")
                    .append(entry.getValue().getPesel());
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public Map<Book, Customer> mapDeadlineComing() {
        Map<Book, Customer> coming = new HashMap<>();
        for (Map.Entry<Book, Customer> entry : this.rentals.entrySet()) {
            if ((entry.getKey().getReturnDeadLine().isAfter(LocalDate.now()) || entry.getKey().getReturnDeadLine().isEqual(LocalDate.now()))
                    && entry.getKey().getReturnDeadLine().isBefore(LocalDate.now().plusDays(4))) {
                coming.put(entry.getKey(), entry.getValue());
            }
        }
        return coming;
    }

    @Override
    public Map<Book, Customer> mapDeadlineExceeded() {
        Map<Book, Customer> exceeded = new HashMap<>();
        for (Map.Entry<Book, Customer> entry : this.rentals.entrySet()) {
            if(entry.getKey().getReturnDeadLine().isBefore(LocalDate.now())) {
                exceeded.put(entry.getKey(), entry.getValue());
            }
        }
        return exceeded;
    }

    @Override
    public void saveData() {
        this.saveCatalogue();
        this.saveCustomers();
        this.saveRentals();
    }

    @Override
    public void saveCustomers() {
        try {
            FileOutputStream fos = new FileOutputStream("customers.txt");
            ObjectOutputStream oot = new ObjectOutputStream(fos);
            oot.writeObject(this.customers);
            oot.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveCatalogue() {
        try {
            FileOutputStream fos = new FileOutputStream("catalogue.txt");
            ObjectOutputStream oot = new ObjectOutputStream(fos);
            oot.writeObject(this.catalogue);
            oot.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveRentals() {
        try {
            FileOutputStream fos = new FileOutputStream("rentals.txt");
            ObjectOutputStream oot = new ObjectOutputStream(fos);
            oot.writeObject(this.rentals);
            oot.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readData() {
        this.readCatalogue();
        this.readCustomers();
        this.readRentals();
    }

    @Override
    public void readCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("customers.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            customers = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.customers = customers;
    }

    @Override
    public void readCatalogue() {
        ArrayList<Book> catalogue = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("catalogue.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            catalogue = (ArrayList) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.catalogue = catalogue;
    }

    @Override
    public void readRentals() {
        HashMap<Book, Customer> rentals = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream("rentals.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            rentals = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.rentals = rentals;
    }
}
