package validators;

import org.junit.Assert;
import org.junit.Test;

public class EmailValidatorTest {
    @Test
    public void shouldCheckIfEmailIsValid(){
        //given
        String email = "sample@email.com";
        //when
        boolean result = EmailValidator.validate(email);
        //then
        Assert.assertTrue(result);
    }
    @Test
    public void shouldCheckIfEmailIsNotValid(){
        //given
        String email = "sample.com";
        boolean result = EmailValidator.validate(email);
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfEmailIsEmpty(){
        //given
        String email = "";
        //when
        boolean result = EmailValidator.validate(email);
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfEmailIsNull(){
        //given
        String email = null;
        //when
        boolean result = EmailValidator.validate(email);
        //then
        Assert.assertFalse(result);
    }
}