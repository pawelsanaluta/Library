package validators;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnumValidatorTest {
    @Test
    public void shouldCheckIfNull(){
        //given
        String toValidate = null;
        //when
        boolean result = EnumValidator.enumValidate(toValidate);
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfEmpty(){
        //given
        String toValidate = "";
        //when
        boolean result = EnumValidator.enumValidate(toValidate);
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfContainsSpace(){
        //given
        String toValidate = " ";
        //when
        boolean result = EnumValidator.enumValidate(toValidate);
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfIncorrect(){
        //given
        String toValidate = "nowel";
        //when
        boolean result = EnumValidator.enumValidate(toValidate);
        //then
        Assert.assertFalse(result);
    }
    @Test
    public void shouldCheckIfCorrect(){
        //given
        String toValidate = "novel";
        //when
        boolean result = EnumValidator.enumValidate(toValidate);
        //then
        Assert.assertTrue(result);
    }
}