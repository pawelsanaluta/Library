package customer;

import validators.EmailValidator;
import validators.EmptyValidator;
import validators.PeselValidator;
import validators.PhoneValidator;

import java.util.Map;

public class Customer {
    private String firstName;

    public String getEmail() {
        return email;
    }

    private String lastName;
    private String pesel;
    private String email;
    private String phoneNumber;
    private Map<Approvals, Boolean> approvals;
    private Address address;

    public Customer(String firstName, String lastName, String pesel, String email, String phoneNumber,
                    Map<Approvals, Boolean> approvals, Address address) throws IllegalArgumentException {
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
        if (EmailValidator.validate(email)){
            this.email = email;
        }
        else{
            throw new IllegalArgumentException("Niepoprawny email");
        }
        if (PhoneValidator.validate(phoneNumber)){
            this.phoneNumber = phoneNumber;
        }
        else{
            throw new IllegalArgumentException("Niepoprawny numer telefonu");
        }
        this.address = address;

        this.approvals = approvals;
    }
}
