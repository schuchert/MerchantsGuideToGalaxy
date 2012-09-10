package com.shoe.mgttg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RomanNumberalSubtractionTest {
    private char from;
    private char to;
    private boolean expected;

    @Parameters
    public static Collection data() {
        ArrayList<Object[]> values = new ArrayList<Object[]>();

        add(values, 'I', 'V', true);
        add(values, 'I', 'X', true);
        add(values, 'I', 'L', false);
        add(values, 'I', 'C', false);
        add(values, 'I', 'D', false);
        add(values, 'I', 'M', false);

        add(values, 'X', 'L', true);
        add(values, 'X', 'C', true);
        add(values, 'X', 'D', false);
        add(values, 'X', 'M', false);

        add(values, 'C', 'D', true);
        add(values, 'C', 'M', true);

        add(values, 'V', 'X', false);
        add(values, 'V', 'L', false);
        add(values, 'V', 'C', false);
        add(values, 'V', 'D', false);
        add(values, 'V', 'M', false);

        add(values, 'X', 'L', true);
        add(values, 'X', 'C', true);
        add(values, 'X', 'D', false);
        add(values, 'X', 'M', false);

        add(values, 'L', 'C', false);
        add(values, 'L', 'D', false);
        add(values, 'L', 'M', false);

        add(values, 'D', 'M', false);

        return values;
    }

    private static void add(ArrayList<Object[]> values, char from, char to, boolean shouldBeAllowed) {
        values.add(new Object[] {from, to, shouldBeAllowed});
    }

    public RomanNumberalSubtractionTest(char from, char to, boolean expected) {
        this.from = from;
        this.to = to;
        this.expected = expected;
    }

    @Test
    public void checkSubtractionPair() {
        boolean success;

        try {
            new RomanNumeralConverter().convert(Character.toString(from) + to);
            success = true;
        } catch (IllegalArgumentException e) {
            success = false;
        }

        assertEquals(String.format("%c -> %c should have been %s", from, to, expected), expected, success);
    }
}
