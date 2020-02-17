package library;

import book.Book;
import book.Category;
import book.Condition;
import customer.Address;
import customer.Customer;
import org.apache.http.util.Asserts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class LibraryTest {

    @Before
    public void before() {
        Library.getCatalogue().clear();
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
        Library library = new Library("KUL");
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
        Library library = new Library("KUL");
        Book book = library.createBook(title, author, released, category, condition, pages, publisher);
        library.addBook(book);

        //then
        Assert.assertEquals(1, Library.getCatalogue().size());
        assertSame(book, Library.getCatalogue().get(0));
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
        Library library = new Library("KUL");
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
        Library library = new Library("KUL");
        Book book = library.createBook(title, author, released, category, condition, pages, publisher);
        Book book1 = library.createBook(title1, author1, released1, category1, condition1, pages1, publisher1);
        String id = book.getId();
        library.addBook(book);
        library.addBook(book1);
        library.removeBook(id);

        //then
        Assert.assertEquals(1, Library.getCatalogue().size());
    }

    @Test
    public void shouldReturnBookByKeyword() {
        //given
        String keyword = "bib";

        //when
        Library library = new Library("KUL");
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
        Library library = new Library("KUL");
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
        Library library = new Library("KUL");
        Customer customer = library.createAndAddCustomer(firstName, lastName, pesel, email, phoneNumber, null);

        //then
        Assert.assertEquals(1, Library.getCustomers().size());
        Assert.assertSame(customer, Library.getCustomers().get(0));
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
        Library library = new Library("KUL");
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
        Library library = new Library("KUL");
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
    public void shouldCheckIfCustomerDataIsEditable() {
        //given
        String firstName = "Jan";
        String lastName = "Paweł";
        String pesel = "75031627968";
        String email = "jan.pawel@drugi.tv";
        String phoneNumber = "666666666";
        Address address = null;
        Library library = new Library("KUL");
        Customer customer = library.createAndAddCustomer(firstName, lastName, pesel, email, phoneNumber, address);
//        Customer customer1 = library.createAndAddCustomer("Amon", "Amrath", "76123006614",
//                "aa@aa.aa", "123456789",
//                new Address("Jakaś", "Miasto", "1", "20-000"));
        //when
        Customer edited = library.searchCustomerByPesel(pesel);
        edited.setFirstName("Karol");
        edited.setLastName("Paweł");
        edited.setAddress(new Address("Limbo", "Hell", "69", "66-666"));
        edited.setPhoneNumber("999999999");
        edited.setEmail("fuckYou@you.motherfucker");
        boolean isCustomerPeselBeforeAndAfterEditTheSame = customer.getPesel().equals(edited.getPesel());
        //then
        Assert.assertTrue(isCustomerPeselBeforeAndAfterEditTheSame);
        Assert.assertEquals(1, Library.getCustomers().size());
        Assert.assertEquals(lastName, edited.getLastName());
        Assert.assertNotEquals(firstName, edited.getFirstName());
        Assert.assertNotEquals(email, edited.getEmail());
        Assert.assertNotEquals(phoneNumber, edited.getPhoneNumber());
        Assert.assertNotEquals(address, edited.getAddress());
    }


    @Test
    public void shouldShowCustomersRentals() {
        //given
        Library library = new Library("KUL");
        Book book1 = library.createBook("bib", "god", 0, Category.FANTASY, Condition.GOOD, 1000, "heaven");
        Book book2 = library.createBook("bib1", "god1", 1, Category.FANTASY, Condition.GOOD, 999, "hell");
        library.addBook(book1);
        library.addBook(book2);
        Customer customer = library.createAndAddCustomer("Jan", "pawel", "88012907557", "jan.pawel@drugi.pl", "123456789", null);
        library.rentBook(book1.getId(), customer.getPesel());
        library.rentBook(book2.getId(), customer.getPesel());

        System.out.println(library.showCustomerRentals(customer.getPesel()));
    }

}