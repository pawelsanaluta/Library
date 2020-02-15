package customer;

import validators.EmptyValidator;
import validators.PeselValidator;

import java.util.Map;

public class Customer {
    private String firstName;
    private String lastName;
    private String pesel;
    private String email;
    private String phoneNumber;
    private Map<Approvals, Boolean> approvals;
    private Address address;

    public Customer(String firstName, String lastName, String pesel, String email, String phoneNumber,
                    Map<Approvals, Boolean> approvals, Address address) throws IllegalAccessException {
        if (EmptyValidator.validator(firstName)){
            this.firstName = firstName;
        }else {
            throw new IllegalAccessException("Niepoprawne imię");
        }
        if (EmptyValidator.validator(lastName)){
            this.lastName = lastName;
        }else{
            throw new IllegalAccessException("Niepoprawne nazwisko");
        }
        if (PeselValidator.validator(pesel)) {
            this.pesel = pesel;
        }else
            throw new IllegalAccessException("Niepoprawne pesel");
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.approvals = approvals;
        this.address = address;
    }
}
