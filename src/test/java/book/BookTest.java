package book;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class BookTest {

    @Test
    public void shouldCreateNewCustomer() {
        //given
        String title = "Bible";
        String authorFirstName = "God";
        String authorLastName = "Almighty";
        String id = Book.createID();
        int released = -10000;
        LocalDate returnDeadLine = null;
        Category category = Category.FANTASY;
        Condition condition = Condition.GOOD;
        int pages = 1000;
        String publisher = "Heaven";

        //when
        Book book = new Book(title, authorFirstName, authorLastName, id, released, category, condition, pages, publisher);

        //then
        Assert.assertEquals(title, book.getTitle());
        Assert.assertEquals(authorFirstName, book.getAuthorFirstName());
        Assert.assertEquals(authorLastName, book.getAuthorLastName());
        Assert.assertEquals(id, book.getId());
        Assert.assertEquals(released, book.getReleased());
        Assert.assertEquals(category, book.getCategory());
        Assert.assertEquals(condition, book.getCondition());
        Assert.assertEquals(pages, book.getPages());
        Assert.assertEquals(publisher, book.getPublisher());

    }
}