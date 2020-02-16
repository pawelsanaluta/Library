package customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String street;
    private String city;
    private String houseNumber;
    private String zipCode;

    public Address(String street, String city, String houseNumber, String zipCode) {
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
    }
}
