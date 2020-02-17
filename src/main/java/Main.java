import customer.Address;
import customer.Customer;
import library.Library;

public class Main {
    public static void main(String[] args) {
       System.out.println("chuj");
        Customer customer = new Customer("Lucyfer", "SejtanSon", "70060146936", "wreoigj@erpoghj.pl",
                "123456789", null);
        Library library = new Library("Kul");
        library.createAndAddCustomer("chuj", "dupa", "70060146936", "wreoigj@erpoghj.pl",
                "123456789", new Address("Limbo", "Hell", "69", "66-666"));
    }

}