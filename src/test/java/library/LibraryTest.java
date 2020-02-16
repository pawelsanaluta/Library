package library;

import book.Book;
import book.Category;
import book.Condition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class LibraryTest {

    @Before
    public void before(){
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
    public void shouldAddBookToTHeLibrary() {
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

}