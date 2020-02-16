package validators;

import customer.Address;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressValidatorTest {
    @Test
    public void shouldCheckIfCityIsNull(){
        //given
        String city = null;
        //when
        boolean result = AddressValidator.validateCity(city);
        //then
        Assert.assertFalse(result);

    }

}