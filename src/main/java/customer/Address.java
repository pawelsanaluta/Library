package customer;

import lombok.Getter;
import lombok.Setter;
import validators.AddressValidator;

import java.io.Serializable;

@Getter
@Setter
public class Address implements Serializable {
    private String street;
    private String city;
    private String addressNumber;
    private String zipCode;

    private static final long serialVersionUID = 1L;

    public Address(String street, String city, String addressNumber, String zipCode) {
        if (AddressValidator.validateStreet(street)) {
            this.street = street;
        } else {
            throw new IllegalArgumentException("Niepoprawna nazwa ulicy");
        }
        if (AddressValidator.validateCity(city)) {
            this.city = city;
        } else {
            throw new IllegalArgumentException("Niepoprawna nazwa miasta");
        }
        if (AddressValidator.validateAddressNumber(addressNumber)) {
            this.addressNumber = addressNumber;
        } else {
            throw new IllegalArgumentException("Niepoprawny numer");
        }
        if (AddressValidator.validateZipCode(zipCode)) {
            this.zipCode = zipCode;
        } else {
            throw new IllegalArgumentException("Niepoprawny kod pocztowy");
        }
    }

    @Override
    public String toString() {
        return "Address: " + city + " " + zipCode + " ul. " + street + " " + addressNumber;
    }
}
