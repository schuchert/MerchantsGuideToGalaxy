package com.shoe.mgttg;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

public class RomanNumeralConverterShould {
    @Test
    public void convertMMVIto1000() {
        assertEquals(new BigDecimal(1000), new RomanNumeralConverter().convert("MMVI"));
    }
}
