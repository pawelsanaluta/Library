package book;

import lombok.*;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDate;

@Getter
@Setter
public class Book implements Serializable {

    private String title;
    private String author;
    private String id;
    private int released;
    private LocalDate returnDeadLine;
    private Category category;
    private Condition condition;
    private int pages;
    private String publisher;

    private static final long serialVersionUID = 1L;

    public Book(String title, String author, String id, int released, Category category, Condition condition, int pages, String publisher) {
        this.title = title;
        this.author = author;
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
        char[] c = new char[5];
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            c[i] = ch[random.nextInt(ch.length)];
        }
        return new String(c);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%8s", "Autor: "));
        sb.append(String.format("%-25s ", this.author));
        sb.append(String.format("%8s", "Tytuł: "));
        sb.append(String.format("%-25s ", this.title));
        sb.append(String.format("%10s", "Wydana: "));
        sb.append(String.format("%-8s ", this.released));
        sb.append(String.format("%8s", "Strony: "));
        sb.append(String.format("%-6s ", this.pages));
        sb.append(String.format("%8s", "Wydawnictwo: "));
        sb.append(String.format("%-6s ", this.pages));
        sb.append(String.format("%16s ", "Termin zwrotu: "));
        sb.append(String.format("%-15s ", this.returnDeadLine.toString()));
        sb.append("\n");
        return sb.toString();
    }
}
