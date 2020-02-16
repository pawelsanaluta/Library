package book;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class BookTest {

    @Test
    public void shouldCreateNewBook() {
        //given
        String title = "Bible";
        String author = "God";
        String id = Book.createID();
        int released = -10000;
        LocalDate returnDeadLine = LocalDate.now().plusDays(14);
        Category category = Category.FANTASY;
        Condition condition = Condition.GOOD;
        int pages = 1000;
        String publisher = "Heaven";

        //when
        Book book = new Book(title, author, id, released, category, condition, pages, publisher);
        book.setReturnDeadLine(returnDeadLine);

        //then
        //System.out.println(book.toString());
        Assert.assertEquals(title, book.getTitle());
        Assert.assertEquals(author, book.getAuthor());
        Assert.assertEquals(id, book.getId());
        Assert.assertEquals(released, book.getReleased());
        Assert.assertEquals(category, book.getCategory());
        Assert.assertEquals(condition, book.getCondition());
        Assert.assertEquals(pages, book.getPages());
        Assert.assertEquals(publisher, book.getPublisher());

    }
}