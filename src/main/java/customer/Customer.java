package customer;

import validators.EmailValidator;
import validators.EmptyValidator;
import validators.PeselValidator;
import validators.PhoneValidator;
import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Customer implements Serializable {

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
            throw new IllegalArgumentException("Niepoprawny pesel");
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%8s", "Imię: "));
        sb.append(String.format("%-25s ", this.firstName));
        sb.append(String.format("%8s", "Nazwisko: "));
        sb.append(String.format("%-25s ", this.lastName));
        sb.append(String.format("%5s", "PESEL: "));
        sb.append(String.format("%-12s ", this.pesel));
        sb.append(String.format("%10s", "Email: "));
        sb.append(String.format("%-8s ", this.email));
        sb.append(String.format("%8s", "Numer telefonu: "));
        sb.append(String.format("%-6s ", this.phoneNumber));
        sb.append(String.format("%8s", "Adres: "));
        sb.append(String.format("%-6s ", this.address));
        sb.append(String.format("%8s", "Zgody: "));
        sb.append(String.format("%-6s ", this.approvals));
        return sb.toString();
    }

    public void setApprovals(Approvals approval, boolean value) {
        this.approvals.put(approval, value);
    }
}
