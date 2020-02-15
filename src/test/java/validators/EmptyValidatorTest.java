package validators;

import org.junit.Assert;
import org.junit.Test;
import validators.EmptyValidator;

import static org.junit.Assert.*;

public class EmptyValidatorTest {

    @Test
    public void shouldCheckIfNull(){
        //given
        String toValid = null;
        //when
        boolean result = EmptyValidator.validator(toValid);
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfEmpty(){
        //given
        String toValid = "";
        //when
        boolean result = EmptyValidator.validator(toValid);
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfContainsSpace(){
        //given
        String toValid1 = " abc";
        String toValid2 = " ";
        //when
        boolean result1 = EmptyValidator.validator(toValid1);
        boolean result2 = EmptyValidator.validator(toValid2);
        //then
        Assert.assertTrue(result1);
        Assert.assertFalse(result2);
    }

}