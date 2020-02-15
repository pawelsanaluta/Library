package library;

import book.Book;
import book.Category;
import book.Condition;
import customer.Customer;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Library {

    private String name;
    private List<Book> catalogue;
    private List<Customer> customers;
    private Map<String, Customer> rentals;

    public Library(String name) {
        this.name = name;
        this.catalogue = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new HashMap<>();
    }

    public Book createBook(String title, String authorFirstName, String authorLastName, int released, Category category, Condition condition, int pages, String publisher) {
        String id = Book.createID();
        long count = catalogue.stream().filter(e -> e.getId().equals(id)).count();
        if (count == 0) {
            return new Book(title, authorFirstName, authorLastName, id, released, category, condition, pages, publisher);
        } else {
            return createBook(title, authorFirstName, authorLastName, released, category, condition, pages, publisher);
        }
    }

    public void addBook(Book book) {
        long count = catalogue.stream().filter(e -> e.getId().equals(book.getId())).count();
        if(count == 0) {
            System.out.println("Dodano nową książkę");
            catalogue.add(book);
        } else {
            System.out.println("Ta książka jest już w katalogu");
        }
    }

    public Book getBookByID(String id) {
        Optional<Book> book = catalogue.stream().filter(e -> e.getId().equals(id)).findAny();
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
            catalogue.remove(book);
        }
    }

    public List<Book> searchByKeyword(String keyword) {
        return  catalogue.stream().filter(e -> e.getTitle().contains(keyword) || e.getAuthorFirstName().contains(keyword)
                || e.getAuthorLastName().contains(keyword) || e.getPublisher().contains(keyword)).collect(Collectors.toList());
    }

    public List<Book> searchByCategory(Category category) {
        return catalogue.stream().filter(e -> e.getCategory().equals(category)).collect(Collectors.toList());
    }

}
