package validators;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneValidatorTest {
    @Test
    public void shouldCheckIFNull(){
        //given
        String phoneNumber = null;
        //when
        boolean result = PhoneValidator.validate(phoneNumber);
        //then
        Assert.assertFalse(result);
    }
//    @Test

}