package validators;

import customer.Address;
import org.junit.Assert;
import org.junit.Test;


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
        boolean result = AddressValidator.validateCity(address.getCity());
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfStreetContainsSpace() {
        //given
        String city1 = " ";
        String city2 = "abc ";
        //when
        Address address1 = new Address("", city1, "1", "11111");
        Address address2 = new Address("", city2, "1", "11111");
        boolean result1 = AddressValidator.validateCity(address1.getCity());
        boolean result2 = AddressValidator.validateCity(address2.getCity());
        //then
        Assert.assertFalse(result1);
        Assert.assertTrue(result2);
    }
    @Test
    public void shouldCheckIfCityIsValid() {
        //given
        String city1 = "Lublin";
        String city2 = "Biała Podlaska";
        //when
        Address address1 = new Address("", city1, "1", "11111");
        Address address2 = new Address("", city2, "1", "11111");
        boolean result1 = AddressValidator.validateCity(address1.getCity());
        boolean result2 = AddressValidator.validateCity(address2.getCity());
        //then
        Assert.assertTrue(result1);
        Assert.assertTrue(result2);
    }
    @Test
    public void shouldCheckIfStreetIsNull() {
        //given
        String street = null;
        //when
        Address address = new Address(street, "city", "1", "11111");
        boolean result = AddressValidator.validateStreet(address.getStreet());
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfStreetIsEmpty() {
        //given
        String street = "";
        //when
        Address address = new Address(street, "city", "1", "11111");
        boolean result = AddressValidator.validateStreet(address.getStreet());
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIStreetContainsSpace() {
        //given
        String street1 = " ";
        String street2 = "abc ";
        //when
        Address address1 = new Address(street1, "city", "1", "11111");
        Address address2 = new Address(street2, "city", "1", "11111");
        boolean result1 = AddressValidator.validateStreet(address1.getStreet());
        boolean result2 = AddressValidator.validateStreet(address2.getStreet());
        //then
        Assert.assertFalse(result1);
        Assert.assertTrue(result2);
    }
    @Test
    public void shouldCheckIfStreetIsValid() {
        //given
        String street1 = "Dożynkowa";
        String street2 = "Lubelskiego Lipca '80";
        //when
        Address address1 = new Address(street1, "city", "1", "11111");
        Address address2 = new Address(street2, "city", "1", "11111");
        boolean result1 = AddressValidator.validateStreet(address1.getStreet());
        boolean result2 = AddressValidator.validateStreet(address2.getStreet());
        //then
        Assert.assertTrue(result1);
        Assert.assertTrue(result2);
    }
    @Test
    public void shouldCheckIfNumberIsNull() {
        //given
        String number = null;
        //when
        Address address = new Address("street", "city", number, "11111");
        boolean result = AddressValidator.validateAddressNumber(address.getHouseNumber());
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfNumberIsEmpty() {
        //given
        String number = "";
        //when
        Address address = new Address("street", "city", number, "11111");
        boolean result = AddressValidator.validateAddressNumber(address.getHouseNumber());
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfNumberContainsSpace() {
        //given
        String number1 = " ";
        String number2 = "10c ";
        //when
        Address address1 = new Address("street", "city", number1, "11111");
        Address address2 = new Address("street", "city", number2, "11111");
        boolean result1 = AddressValidator.validateAddressNumber(address1.getHouseNumber());
        boolean result2 = AddressValidator.validateAddressNumber(address2.getHouseNumber());
        //then
        Assert.assertFalse(result1);
        Assert.assertTrue(result2);
    }
    @Test
    public void shouldCheckIfNumberIsValid() {
        //given
        String number1 = "10";
        String number2 = "10c";
        //when
        Address address1 = new Address("street", "city", number1, "11111");
        Address address2 = new Address("street", "city", number2, "11111");
        boolean result1 = AddressValidator.validateAddressNumber(address1.getHouseNumber());
        boolean result2 = AddressValidator.validateAddressNumber(address2.getHouseNumber());
        //then
        Assert.assertTrue(result1);
        Assert.assertTrue(result2);
    }
    @Test
    public void shouldCheckIfZipCodeIsNull(){
        //given
        String zipCode = null;
        //when
        Address address = new Address("street", "city", "1", zipCode);
        boolean result = AddressValidator.validateZipCode(address.getZipCode());
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfZipCodeIsEmpty(){
        //given
        String zipCode = "";
        //when
        Address address = new Address("street", "city", "1", zipCode);
        boolean result = AddressValidator.validateZipCode(address.getZipCode());
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfZipCodeContainsSpace(){
        //given
        String zipCode = " ";
        //when
        Address address = new Address("street", "city", "1", zipCode);
        boolean result = AddressValidator.validateZipCode(address.getZipCode());
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfZipCodeHas6Chars(){
        //given
        String zipCode = "asdfgr";
        //when
        Address address = new Address("street", "city", "1", zipCode);
        boolean result = AddressValidator.validateZipCode(address.getZipCode());
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfZipCodeHas6Numbers(){
        //given
        String zipCode = "214456";
        //when
        Address address = new Address("street", "city", "1", zipCode);
        boolean result = AddressValidator.validateZipCode(address.getZipCode());
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfZipCodeHas5Chars(){
        //given
        String zipCode = "assfe";
        //when
        Address address = new Address("street", "city", "1", zipCode);
        boolean result = AddressValidator.validateZipCode(address.getZipCode());
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfZipCodeHas5Numbers(){
        //given
        String zipCode = "12456";
        //when
        Address address = new Address("street", "city", "1", zipCode);
        boolean result = AddressValidator.validateZipCode(address.getZipCode());
        //then
        Assert.assertTrue(result);
    }
    @Test
    public void shouldCheckIfZipCodeHas5NumbersAndOneChar(){
        //given
        String zipCode1 = "12456-";
        String zipCode2 = "1-2456";
        //when
        Address address1 = new Address("street", "city", "1", zipCode1);
        Address address2 = new Address("street", "city", "1", zipCode2);
        boolean result1 = AddressValidator.validateZipCode(address1.getZipCode());
        boolean result2 = AddressValidator.validateZipCode(address2.getZipCode());
        //then
        Assert.assertFalse(result1);
        Assert.assertFalse(result2);
    }
    @Test
    public void shouldCheckIfZipCodeHas5NumbersAndOneCharAtGoodIndex(){
        //given
        String zipCode1 = "12-456";
        String zipCode2 = "1a-456";
        //when
        Address address1 = new Address("street", "city", "1", zipCode1);
        Address address2 = new Address("street", "city", "1", zipCode2);
        boolean result1 = AddressValidator.validateZipCode(address1.getZipCode());
        boolean result2 = AddressValidator.validateZipCode(address2.getZipCode());
        //then
        Assert.assertTrue(result1);
        Assert.assertFalse(result2);
    }
}