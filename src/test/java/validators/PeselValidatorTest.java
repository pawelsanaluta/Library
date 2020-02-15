package validators;

import org.junit.Assert;
import org.junit.Test;

public class PeselValidatorTest {
    @Test
    public void shouldCheckIfNull() {
        //given
        String pesel = null;
        //when
        boolean result = PeselValidator.validate(pesel);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckIfEmpty() {
        //given
        String pesel = "";
        //when
        boolean result = PeselValidator.validate(pesel);
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
        boolean result1 = PeselValidator.validate(peselWith11Chars);
        boolean result2 = PeselValidator.validate(peselWith12Chars);
        boolean result3 = PeselValidator.validate(peselWith10Chars);
        //then
        Assert.assertFalse(result1);
        Assert.assertFalse(result2);
        Assert.assertFalse(result3);
    }

    @Test
    public void shouldCheckIfTooManyChars() {
        //given
        String peselWith12Chars = "a20611356823";
        //when
        boolean result = PeselValidator.validate(peselWith12Chars);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckIfNumbers() {
        //given
        String pesel = "qq051474776";
        //when
        boolean result = PeselValidator.validate(pesel);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckIf11Numbers() {
        //when
        String pesel = "12345678912";
        boolean result = PeselValidator.validate(pesel);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckIfPesel() {
        //given
        String pesel1 = "91110829942";
        String pesel2 = "11110829942";
        //when
        boolean result1 = PeselValidator.validate(pesel1);
        boolean result2 = PeselValidator.validate(pesel2);
        //then
        Assert.assertTrue(result1);
        Assert.assertFalse(result2);
    }
}