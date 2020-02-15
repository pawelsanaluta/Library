package book;

import lombok.*;

import java.security.SecureRandom;
import java.time.LocalDate;

@Getter
@Setter
public class Book {

    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String id;
    private int released;
    private LocalDate returnDeadLine;
    private Category category;
    private Condition condition;
    private int pages;
    private String publisher;

    public Book(String title, String authorFirstName, String authorLastName, String id, int released, Category category, Condition condition, int pages, String publisher) {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.id = id;
        this.released = released;
        this.category = category;
        this.condition = condition;
        this.pages = pages;
        this.publisher = publisher;
        this.returnDeadLine = null;
    }

    public static String createID() {
        char[] ch = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] c = new char[10];
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            c[i] = ch[random.nextInt(ch.length)];
        }
        return new String(c);
    }
}
