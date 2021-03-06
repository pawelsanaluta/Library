package library;

import book.Book;
import book.Category;
import book.Condition;
import customer.Address;
import customer.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LibraryTest {

    private String catalogueFileName = "test_catalogue.txt";
    private String customersFileName = "test_customers.txt";
    private String rentalsFileName = "test_rentals.txt";

    @Before
    public void before() {
        Library.getInstance().getRentals().clear();
        Library.getInstance().getCatalogue().clear();
        Library.getInstance().getCustomers().clear();
    }

    @After
    public void after() throws IOException {
        BufferedWriter bw1 = new BufferedWriter(new FileWriter("test_catalogue.txt"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("test_customers.txt"));
        BufferedWriter bw3 = new BufferedWriter(new FileWriter("test_rentals.txt"));
        bw1.write("");
        bw2.write("");
        bw3.write("");
    }

    @Test
    public void shouldCreateNewBook() {
        //given
        String title = "Bible";
        String author = "God";
        int released = -10000;
        LocalDate returnDeadLine = null;
        Category category = Category.FANTASY;
        Condition condition = Condition.GOOD;
        int pages = 1000;
        String publisher = "Heaven";

        //when
        Library library = Library.getInstance();
        Book book = library.createBook(title, author, released, category, condition, pages, publisher);

        //then
        Assert.assertEquals(title, book.getTitle());
        Assert.assertEquals(author, book.getAuthor());
        Assert.assertEquals(returnDeadLine, book.getReturnDeadLine());
        Assert.assertEquals(category, book.getCategory());
        Assert.assertEquals(condition, book.getCondition());
        Assert.assertEquals(pages, book.getPages());
        Assert.assertEquals(publisher, book.getPublisher());
    }

    @Test
    public void shouldAddBookToTheLibrary() {
        //given
        String title = "Bible";
        String author = "God";
        int released = -10000;
        Category category = Category.FANTASY;
        Condition condition = Condition.GOOD;
        int pages = 1000;
        String publisher = "Heaven";

        //when
        Library library = Library.getInstance();
        Book book = library.createBook(title, author, released, category, condition, pages, publisher);
        library.addBook(book);

        //then
        Assert.assertEquals(1, library.getCatalogue().size());
        assertSame(book, library.getCatalogue().get(0));
    }

    @Test
    public void shouldReturnBookByID() {
        //given
        String title = "Bible";
        String author = "God";
        int released = -10000;
        Category category = Category.FANTASY;
        Condition condition = Condition.GOOD;
        int pages = 1000;
        String publisher = "Heaven";

        //when
        Library library = Library.getInstance();
        Book book = library.createBook(title, author, released, category, condition, pages, publisher);
        String id = book.getId();
        library.addBook(book);

        //then
        Assert.assertSame(book, library.getBookByID(id));
    }

    @Test
    public void shouldRemoveBookByID() {
        //given
        String title = "Bible";
        String author = "God";
        int released = -10000;
        Category category = Category.FANTASY;
        Condition condition = Condition.GOOD;
        int pages = 1000;
        String publisher = "Heaven";

        String title1 = "Katechizm kościoła katolickiego";
        String author1 = "God";
        int released1 = 1000;
        Category category1 = Category.FANTASY;
        Condition condition1 = Condition.GOOD;
        int pages1 = 1000;
        String publisher1 = "Heaven";

        //when
        Library library = Library.getInstance();
        Book book = library.createBook(title, author, released, category, condition, pages, publisher);
        Book book1 = library.createBook(title1, author1, released1, category1, condition1, pages1, publisher1);
        String id = book.getId();
        library.addBook(book);
        library.addBook(book1);
        library.removeBook(id);

        //then
        Assert.assertEquals(1, library.getCatalogue().size());
    }

    @Test
    public void shouldReturnBookByKeyword() {
        //given
        String keyword = "bib";

        //when
        Library library = Library.getInstance();
        Book book1 = library.createBook("Biblia", "dasd", 1000, Category.CRIMINAL, Condition.GOOD, 1000, "Heaven");
        Book book2 = library.createBook("Dupa", "dsa", 2000, Category.FANTASY, Condition.BAD, 200, "Helion");
        library.addBook(book1);
        library.addBook(book2);
        List<Book> searchResult = library.searchByKeyword(keyword);

        //then
        Assert.assertEquals(1, searchResult.size());
        Assert.assertSame(book1, searchResult.get(0));
    }

    @Test
    public void shouldReturnBookByCategory() {
        //given
        Category category = Category.FANTASY;

        //when
        Library library = Library.getInstance();
        Book book1 = library.createBook("Biblia", "dasd", 1000, Category.FANTASY, Condition.GOOD, 1000, "Heaven");
        library.addBook(book1);
        List<Book> searchResult = library.searchByCategory(Category.FANTASY);

        //then
        Assert.assertEquals(1, searchResult.size());
        Assert.assertSame(book1, searchResult.get(0));
    }

    @Test
    public void shouldCreateAndAddCustomer() {
        //given
        String firstName = "Jan";
        String lastName = "Paweł";
        String pesel = "75031627968";
        String email = "jan.pawel@drugi.vt";
        String phoneNumber = "666666666";
        Address address = null;

        //when
        Library library = Library.getInstance();
        Customer customer = library.createAndAddCustomer(firstName, lastName, pesel, email, phoneNumber, null);

        //then
        Assert.assertEquals(1, library.getCustomers().size());
        Assert.assertSame(customer, library.getCustomers().get(0));
    }

    @Test
    public void shouldReturnCustomerByPesel() {
        //given
        String firstName = "Jan";
        String lastName = "Paweł";
        String pesel = "75031627968";
        String email = "jan.pawel@drugi.vt";
        String phoneNumber = "666666666";
        Address address = null;

        //when
        Library library = Library.getInstance();
        Customer customer1 = library.createAndAddCustomer(firstName, lastName, pesel, email, phoneNumber, null);
        Customer customer2 = library.searchCustomerByPesel("75031627968");
        Customer customer3 = library.createAndAddCustomer(firstName, lastName, "82102945762", email, phoneNumber, null);
        boolean result = customer2.equals(customer3);

        //then
        Assert.assertFalse(result);
    }

    @Test
    public void shouldReturnCustomerByName() {
        //given
        String firstName = "Jan";
        String lastName = "Paweł";
        String pesel = "75031627968";
        String email = "jan.pawel@drugi.tv";
        String phoneNumber = "666666666";
        Address address = null;

        String firstName1 = "Karol";
        String lastName1 = "Wojtyła";
        String pesel1 = "82102945762";
        String email1 = "karol.wojtyla69@hell.tv";
        String phoneNumber1 = "666666666";
        Address address1 = null;

        //when
        Library library = Library.getInstance();
        List<Customer> customerList;
        List<Customer> customerList1;
        Customer customer1 = library.createAndAddCustomer(firstName, lastName, pesel, email, phoneNumber, address);
        Customer customer2 = library.createAndAddCustomer(firstName1, lastName1, pesel1, email1, phoneNumber1, address1);
        customerList = library.searchCustomerByName(firstName, lastName);
        customerList1 = library.searchCustomerByName(firstName1, lastName1);

        boolean result = customer1.equals(customerList.get(0));
        boolean result1 = customer2.equals(customerList.get(0));
        boolean result2 = customerList.get(0).getPesel().equals(customerList1.get(0).getPesel());

        //then
        Assert.assertEquals(1, customerList.size());
        Assert.assertEquals(customer1.getFirstName(), customerList.get(0).getFirstName());
        Assert.assertEquals(customer1.getLastName(), customerList.get(0).getLastName());
        Assert.assertEquals(customer1.getPesel(), customerList.get(0).getPesel());
        Assert.assertFalse(result1);
        Assert.assertTrue(result);
        Assert.assertFalse(result1);
        Assert.assertFalse(result2);
    }

    @Test
    public void shouldModifyCustomerData() {
        //given
        String firstName1 = "Jan";
        String lastName1 = "Paweł";
        String pesel1 = "75031627968";
        String email1 = "jan.pawel@drugi.tv";
        String phoneNumber1 = "666666666";
        Address address1 = new Address("jaroslawa", "lublin", "8", "20-100");
        Library library = Library.getInstance();
        Customer customer = library.createAndAddCustomer(firstName1, lastName1, pesel1, email1, phoneNumber1, address1);

        //when
        String firstName2 = "Benedykt";
        String lastName2 = "Szesnasty";
        String email2 = "ben.16@vatican.vt";
        String phoneNumber2 = "123456789";
        Address address2 = new Address("jana", "radom", "910", "15-000");
        library.editCustomerFirstName("75031627968", firstName2);
        library.editCustomerLastName("75031627968", lastName2);
        library.editCustomerEmail("75031627968", email2);
        library.editCustomerPhoneNumber("75031627968", phoneNumber2);
        library.editCustomerAddress("75031627968", address2);

        boolean result1 = customer.getFirstName().equals(firstName2);
        boolean result2 = customer.getLastName().equals(lastName2);
        boolean result3 = customer.getEmail().equals(email2);
        boolean result4 = customer.getPhoneNumber().equals(phoneNumber2);
        boolean result5 = customer.getAddress().equals(address2);

        //then
        Assert.assertTrue(result1);
        Assert.assertTrue(result2);
        Assert.assertTrue(result3);
        Assert.assertTrue(result4);
        Assert.assertTrue(result5);
    }

    @Test
    public void shouldShowCustomersRentals() {
        //given
        Library library = Library.getInstance();
        Book book1 = library.createBook("bib", "god", 0, Category.FANTASY, Condition.GOOD, 1000, "heaven");
        Book book2 = library.createBook("bib1", "god1", 1, Category.FANTASY, Condition.GOOD, 999, "hell");
        library.addBook(book1);
        library.addBook(book2);
        Customer customer = library.createAndAddCustomer("Jan", "pawel", "79051804437", "jan.pawel@drugi.pl", "123456789", null);
        library.rentBook(book1.getId(), customer.getPesel());
        library.rentBook(book2.getId(), customer.getPesel());

        System.out.println(library.showCustomerRentals(customer.getPesel()));
    }

    @Test
    public void shouldRemoveCustomer() {
        //given
        Library library1 = Library.getInstance();
        library1.createAndAddCustomer("Jan", "pawel", "67092102689", "jan@pawel.dr", "123456789", null);
        library1.createAndAddCustomer("Ben", "szesnasty", "79051804437", "ben@16.vt", "987654321", null);
        library1.createAndAddCustomer("Fra", "pierwszy", "98112804528", "fran@ciszek.dr", "321654987", null);

        //when
        library1.removeCustomer("98112804528");
        library1.removeCustomer("79051804437");
        long count1 = library1.getCustomers().stream().filter(e -> e.getPesel().equals("98112804528")).count();
        long count2 = library1.getCustomers().stream().filter(e -> e.getPesel().equals("79051804437")).count();
        long count3 = library1.getCustomers().stream().filter(e -> e.getPesel().equals("67092102689")).count();

        //then
        Assert.assertEquals(0, count1);
        Assert.assertEquals(0, count2);
        Assert.assertEquals(1, count3);
    }

    @Test
    public void shouldRemoveObjectFromRentals() {
        //given
        Library library = Library.getInstance();
        Book book1 = library.createBook("bib", "god", 0, Category.FANTASY, Condition.GOOD, 1000, "heaven");
        Book book2 = library.createBook("bib1", "god1", 1, Category.FANTASY, Condition.GOOD, 999, "hell");
        library.addBook(book1);
        library.addBook(book2);
        Customer customer = library.createAndAddCustomer("Jan", "pawel", "79051804437", "jan.pawel@drugi.pl", "123456789", null);
        library.rentBook(book1.getId(), customer.getPesel());
        library.rentBook(book2.getId(), customer.getPesel());

        //when
        library.returnBook(book1.getId());
        boolean result1 = library.getRentals().containsKey(book1);
        boolean result2 = library.getRentals().containsKey(book2);

        //then
        Assert.assertFalse(result1);
        Assert.assertTrue(result2);
    }

    //ioioioioioioioioioioioioioioioioioioioioioioioioioioioioioioioioioioioioioioio
    @Test
    public void shouldSaveAndReadCatalogue() {
        //given
        Library library = Library.getInstance();
        Book book1 = library.createBook("bib", "god", 0, Category.FANTASY, Condition.GOOD, 1000, "heaven");
        Book book2 = library.createBook("bib1", "god1", 1, Category.FANTASY, Condition.GOOD, 999, "hell");
        Book book3 = library.createBook("bib2", "god2", 1, Category.FANTASY, Condition.BAD, 998, "jesus");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.saveCatalogue(catalogueFileName);

        //when
        library.getCatalogue().clear();
        library.readCatalogue(catalogueFileName);
        boolean result1 = library.getCatalogue().get(0).getTitle().equals(book1.getTitle());
        boolean result2 = library.getCatalogue().get(1).getTitle().equals(book2.getTitle());
        boolean result3 = library.getCatalogue().get(2).getTitle().equals(book3.getTitle());

        //then
        Assert.assertTrue(result1);
        Assert.assertTrue(result2);
        Assert.assertTrue(result3);
    }

    @Test
    public void shouldSaveAndReadCustomersDB() {
        //given
        Library library = Library.getInstance();
        library.createAndAddCustomer("Jan", "pawel", "67092102689", "jan@pawel.dr", "123456789", null);
        library.createAndAddCustomer("Ben", "szesnasty", "79051804437", "ben@16.vt", "987654321", null);
        library.createAndAddCustomer("Fra", "pierwszy", "98112804528", "fran@ciszek.dr", "321654987", null);
        library.saveCustomers(customersFileName);

        //when
        library.getCustomers().clear();
        library.readCustomers(customersFileName);
        boolean result1 = library.getCustomers().get(0).getPesel().equals("67092102689");
        boolean result2 = library.getCustomers().get(1).getPesel().equals("79051804437");
        boolean result3 = library.getCustomers().get(2).getPesel().equals("98112804528");

        //then
        Assert.assertTrue(result1);
        Assert.assertTrue(result2);
        Assert.assertTrue(result3);
    }

    @Test
    public void shouldSaveAndReadRentals() {
        //given
        Library library = Library.getInstance();
        library.createAndAddCustomer("Jan", "pawel", "67092102689", "jan@pawel.dr", "123456789", null);
        library.createAndAddCustomer("Ben", "szesnasty", "79051804437", "ben@16.vt", "987654321", null);
        library.createAndAddCustomer("Fra", "pierwszy", "98112804528", "fran@ciszek.dr", "321654987", null);
        library.saveCustomers(customersFileName);
        Book book1 = library.createBook("bib", "god", 0, Category.FANTASY, Condition.GOOD, 1000, "heaven");
        Book book2 = library.createBook("bib1", "god1", 1, Category.FANTASY, Condition.GOOD, 999, "hell");
        Book book3 = library.createBook("bib2", "god2", 1, Category.FANTASY, Condition.BAD, 998, "jesus");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.saveCatalogue(catalogueFileName);
        library.rentBook(book1.getId(), "79051804437");
        library.rentBook(book3.getId(), "79051804437");
        library.saveCatalogue(catalogueFileName);
        library.saveCustomers(customersFileName);
        library.saveRentals(rentalsFileName);

        //when
        library.getCustomers().clear();
        library.getCatalogue().clear();
        library.getRentals().clear();
        library.readCatalogue(catalogueFileName);
        library.readCustomers(customersFileName);
        library.readRentals(rentalsFileName);
        boolean result1 = library.getRentals().size() == 2;
        boolean result2 = library.getRentals().entrySet().stream().anyMatch(e -> e.getKey().getId().equals(book1.getId()));
        boolean result3 = library.getRentals().entrySet().stream().anyMatch(e -> e.getValue().getPesel().equals("79051804437"));

        //then
        Assert.assertTrue(result1);
        Assert.assertTrue(result2);
        Assert.assertTrue(result3);
    }

    @Test
    public void shouldListAvailableBooks() {
        //given
        Library library = Library.getInstance();
        library.createAndAddCustomer("Jan", "pawel", "67092102689", "jan@pawel.dr", "123456789", null);
        library.createAndAddCustomer("Ben", "szesnasty", "79051804437", "ben@16.vt", "987654321", null);
        library.createAndAddCustomer("Fra", "pierwszy", "98112804528", "fran@ciszek.dr", "321654987", null);
        Book book1 = library.createBook("bib", "god", 0, Category.FANTASY, Condition.GOOD, 1000, "heaven");
        Book book2 = library.createBook("bib1", "god1", 1, Category.FANTASY, Condition.GOOD, 999, "hell");
        Book book3 = library.createBook("bib2", "god2", 1, Category.FANTASY, Condition.BAD, 998, "jesus");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        //when
        library.rentBook(book1.getId(), "79051804437");
        library.rentBook(book3.getId(), "79051804437");
        List<Book> available = library.listAvailableBooks();
        boolean result1 = available.get(0).getId().equals(book2.getId());

        //then
        Assert.assertEquals(1, available.size());
        Assert.assertTrue(result1);
    }

    @Test
    public void shouldMapRentalDeadlineComing() {
        //given
        Library library = Library.getInstance();
        library.createAndAddCustomer("Jan", "pawel", "67092102689", "jan@pawel.dr", "123456789", null);
        library.createAndAddCustomer("Ben", "szesnasty", "79051804437", "ben@16.vt", "987654321", null);
        library.createAndAddCustomer("Fra", "pierwszy", "98112804528", "fran@ciszek.dr", "321654987", null);
        Book book1 = library.createBook("bib", "god", 0, Category.FANTASY, Condition.GOOD, 1000, "heaven");
        Book book2 = library.createBook("bib1", "god1", 1, Category.FANTASY, Condition.GOOD, 999, "hell");
        Book book3 = library.createBook("bib2", "god2", 1, Category.FANTASY, Condition.BAD, 998, "jesus");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.rentBook(book1.getId(), "67092102689");
        library.rentBook(book2.getId(), "98112804528");

        //when
        LocalDate returnDate = LocalDate.of(2020, 2,24);
        book1.setReturnDeadLine(returnDate);
        Map<Book, Customer> deadlineComing = library.mapDeadlineComing();
        boolean result = deadlineComing.containsKey(book1);
        boolean result2 = deadlineComing.containsKey(book2);

        //then
        Assert.assertEquals(1, deadlineComing.size());
        Assert.assertTrue(result);
        Assert.assertFalse(result2);

    }

    @Test
    public void shouldMapExceededReturnDeadline() {
        //given
        Library library = Library.getInstance();
        library.createAndAddCustomer("Jan", "pawel", "67092102689", "jan@pawel.dr", "123456789", null);
        library.createAndAddCustomer("Ben", "szesnasty", "79051804437", "ben@16.vt", "987654321", null);
        library.createAndAddCustomer("Fra", "pierwszy", "98112804528", "fran@ciszek.dr", "321654987", null);
        Book book1 = library.createBook("bib", "god", 0, Category.FANTASY, Condition.GOOD, 1000, "heaven");
        Book book2 = library.createBook("bib1", "god1", 1, Category.FANTASY, Condition.GOOD, 999, "hell");
        Book book3 = library.createBook("bib2", "god2", 1, Category.FANTASY, Condition.BAD, 998, "jesus");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        //when
        library.rentBook(book1.getId(), "67092102689");
        library.rentBook(book2.getId(), "98112804528");
        book1.setReturnDeadLine(LocalDate.now().minusDays(3));
        Map<Book, Customer> deadlineExc = library.mapDeadlineExceeded();
        boolean result1 = deadlineExc.containsKey(book1);
        boolean result2 = deadlineExc.containsKey(book2);

        //then
        Assert.assertEquals(1, deadlineExc.size());
        Assert.assertTrue(result1);
        Assert.assertFalse(result2);

    }

    @Test
    public void shouldCreateBookObjectsFromTextFile() throws IOException {
        //given
        String filename = "books.txt";

        //when
        Library library = Library.getInstance();
        library.readBooksFromTextFile(filename);
        int size = library.getCatalogue().size();

        //then
        Assert.assertEquals(5, size);
    }

    @Test
    public void shouldReadCustomerDataFromTextFile() throws IOException {
        //given
        String fileName;


    }
}