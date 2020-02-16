package validators;

import customer.Address;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressValidatorTest {
    @Test
    public void shouldCheckIfCityIsNull() {
        //given
        String city = null;
        //when
        Address address = new Address("", city, "1", "11111");
        boolean result = AddressValidator.validateCity(address.getCity());
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckIfCityIsEmpty() {
        //given
        String city = "";
        //when
        Address address = new Address("", city, "1", "11111");
        boolean result = AddressValidator.validateCity(city);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckIfCityContainsSpace() {
        //given
        String city1 = " ";
        String city2 = "abc ";
        //when
        Address address1 = new Address("", city1, "1", "11111");
        Address address2 = new Address("", city2, "1", "11111");
        boolean result1 = AddressValidator.validateCity(city1);
        boolean result2 = AddressValidator.validateCity(city2);
        //then
        Assert.assertFalse(result1);
        Assert.assertTrue(result2);
    }
    @Test
    public void shouldCheckIfCityIsValid(){
        //given
        String city1 = "Lublin";
        String city2 = "Biała Podlaska";
        //when
        Address address1 = new Address("", city1, "1", "11111");
        Address address2 = new Address("", city2, "1", "11111");
        boolean result1 = AddressValidator.validateCity(city1);
        boolean result2 = AddressValidator.validateCity(city2);
        //then
        Assert.assertTrue(result1);
        Assert.assertTrue(result2);
    }
}