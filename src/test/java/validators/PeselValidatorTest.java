package validators;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PeselValidatorTest {
    @Test
    public void shouldCheckIfNull() {
        //given
        String pesel = null;
        //when
        boolean result = PeselValidator.validator(pesel);
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfEmpty() {
        //given
        String pesel = "";
        //when
        boolean result = PeselValidator.validator(pesel);
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIf11Chars() {
        //given
        String peselWith11Chars = "a2061135682";
        String peselWith12Chars = "a20611356823";
        String peselWith10Chars = "a206113568";
        //when
        boolean result1 = PeselValidator.validator(peselWith11Chars);
        boolean result2 = PeselValidator.validator(peselWith12Chars);
        boolean result3 = PeselValidator.validator(peselWith10Chars);
        //then
        Assert.assertFalse(result1);
        Assert.assertFalse(result2);
        Assert.assertFalse(result3);
    }
//    @Test
//    public void shou


}