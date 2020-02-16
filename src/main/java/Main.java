import customer.Customer;
import library.Library;

public class Main {
    public static void main(String[] args) {
       System.out.println("chuj");
        Customer customer = new Customer("chuj", "dupa", "70060146936", "wreoigj@erpoghj.pl",
                "123456789", null);
        Library library = new Library("Kul");
        library.createAndAddCustomer("chuj", "dupa", "70060146936", "wreoigj@erpoghj.pl",
                "123456789", null);
        customer.showCustomers(Library.getCustomers());
    }

}