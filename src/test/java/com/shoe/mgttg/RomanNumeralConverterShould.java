package com.shoe.mgttg;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

public class RomanNumeralConverterShould {
    RomanNumeralConverter converter;

    @Before
    public void init() {
        converter = new RomanNumeralConverter();
    }

    private void assertConversion(String value, long expected) {
        assertEquals(new BigDecimal(expected), new RomanNumeralConverter().convert(value));
    }

    @Test
    public void nullStringIs0() {
        assertConversion(null, 0);
    }

    @Test
    public void emptyStringIs0() {
        assertConversion("", 0);
    }

    @Test
    public void convertMMVIto1000() {
        assertConversion("MMVI", 2006);
    }

    @Test
    public void convertIto1() {
        assertConversion("I", 1);
    }

    @Test
    public void convertVto5() {
        assertConversion("V", 5);
    }

    @Test
    public void convertXto10() {
        assertConversion("X", 10);
    }

    @Test
    public void convertLto50() {
        assertConversion("L", 50);
    }

    @Test
    public void convertCto100() {
        assertConversion("C", 100);
    }

    @Test
    public void convertDto500() {
        assertConversion("D", 500);
    }

    @Test
    public void convertMto1000() {
        assertConversion("M", 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doesNotAllowInvalidCharacter() {
        converter.convert("p");
    }

    @Test
    public void shouldHandleLowercaseLetters() {
        assertConversion("mdclxvi", 1000 + 500 + 100 + 50 + 10 + 5 + 1);
    }

    @Test
    public void largerSmallerValueBeforeLargerValueIsSubtraction() {
        assertConversion("CM", 900);
    }

    @Test
    public void longerValueIncludingAdditionAndSubtraction() {
        assertConversion("MCMXLIV", 1944);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notAllowMoreThan3IInARow() {
        converter.convert("IIII");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notAllowMoreThan3XInARow() {
        converter.convert("XXXX");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notAllowMoreThan3CInARow() {
        converter.convert("CCCC");
    }

    @Test(expected = IllegalArgumentException.class)
    public void notAllowMoreThan3MInARow() {
        converter.convert("MMMM");
    }
}
