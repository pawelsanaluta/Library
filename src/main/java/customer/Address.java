package customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class Address {
    private String street;
    private String city;
    private String houseNumber;
    private String zipCode;

}
