package customer;

import validators.EmailValidator;
import validators.EmptyValidator;
import validators.PeselValidator;
import validators.PhoneValidator;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class Customer {

    private String firstName;
    private String lastName;
    private String pesel;
    private String email;
    private String phoneNumber;
    private Map<Approvals, Boolean> approvals;
    private Address address;

    public Customer(String firstName, String lastName, String pesel, String email, String phoneNumber,
                    Address address) throws IllegalArgumentException {
        if (EmptyValidator.validate(firstName)) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("Niepoprawne imię");
        }
        if (EmptyValidator.validate(lastName)) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Niepoprawne nazwisko");
        }
        if (PeselValidator.validate(pesel)) {
            this.pesel = pesel;
        } else {
            throw new IllegalArgumentException("Niepoprawne pesel");
        }
        if (EmailValidator.validate(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Niepoprawny email");
        }
        if (PhoneValidator.validate(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Niepoprawny numer telefonu");
        }

        this.address = address;
        this.approvals = new HashMap<>();
        this.approvals.put(Approvals.PERSONAL_DATA_PROCESSING, true);
        this.approvals.put(Approvals.MARKETING, true);
    }

    public void showCustomers(List<Customer> customerList){
        for (Customer c :customerList) {
            System.out.println(getFirstName() + " " + getLastName() + " Pesel: " + getPesel());
            System.out.println("Dane kontaktowe:");
            System.out.print(String.format("Numer telefonu %-20s", c.getPhoneNumber()));
            System.out.print(String.format("Adres email %-20s", c.getEmail()));
            System.out.println(String.format(" %-20s", c.getAddress().toString()));
            System.out.println("Zgody: ");
            System.out.println(String.format("%-20s",c.getApprovals().toString()));

        }
    }
    @Override
    public String toString() {
        return "Klient: " +
                " firstName" + firstName + '\'' +
                " lastName" + lastName + '\'' +
                " pesel" + pesel + '\'' +
                " email" + email + '\'' +
                " phoneNumber" + phoneNumber + '\'' +
                " approvals" + approvals +
                " address" + address;
    }

    public void setApprovals(Approvals approval, boolean value) {
        this.approvals.put(approval, value);
    }
}
