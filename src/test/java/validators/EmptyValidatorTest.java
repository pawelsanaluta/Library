package validators;

import org.junit.Assert;
import org.junit.Test;

public class EmptyValidatorTest {

    @Test
    public void shouldCheckIfNull(){
        //given
        String toValid = null;
        //when
        boolean result = EmptyValidator.validate(toValid);
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfEmpty(){
        //given
        String toValid = "";
        //when
        boolean result = EmptyValidator.validate(toValid);
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfContainsSpace(){
        //given
        String toValid1 = " abc";
        String toValid2 = " ";
        //when
        boolean result1 = EmptyValidator.validate(toValid1);
        boolean result2 = EmptyValidator.validate(toValid2);
        //then
        Assert.assertTrue(result1);
        Assert.assertFalse(result2);
    }

}