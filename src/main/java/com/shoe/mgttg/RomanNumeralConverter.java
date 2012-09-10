package com.shoe.mgttg;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RomanNumeralConverter {
    private static final String REPEAT_LIMITED = "IXCM";

    private final ConcurrentHashMap<Character,BigDecimal> digitToValue;
    private final ConcurrentHashMap<Character, String> validSubtractions;

    public RomanNumeralConverter() {
        digitToValue = new ConcurrentHashMap<Character, BigDecimal>();
        digitToValue.put('I', new BigDecimal(1));
        digitToValue.put('V', new BigDecimal(5));
        digitToValue.put('X', new BigDecimal(10));
        digitToValue.put('L', new BigDecimal(50));
        digitToValue.put('C', new BigDecimal(100));
        digitToValue.put('D', new BigDecimal(500));
        digitToValue.put('M', new BigDecimal(1000));

        validSubtractions = new ConcurrentHashMap<Character, String>();
        validSubtractions.put('I', "VX");
        validSubtractions.put('X', "LC");
        validSubtractions.put('C', "DM");
    }

    public BigDecimal convert(String number) {
        BigDecimal sum = BigDecimal.ZERO;

        char last = 0;
        int count = 0;

        for(int i = 0; inString(i, number); ++i) {
            char c = current(number, i);
            char n = next(number, i);

            if(last == 0 || last != c) {
                last = c;
                count = 1;
            } else if(last == c) {
                ++count;
            }

            if(count > 3 && REPEAT_LIMITED.indexOf(last) != -1) {
                throw new IllegalArgumentException("Cannot repeat I more than 3 times");
            }

            if(nextBigger(c, n))  {
                if(!validSubtractionPair(c, n)) {
                    throw new IllegalArgumentException(c + " can only be followed by " + validSubtractions.get(c));
                }
                sum = sum.subtract(valueFor(c));
            }
            else {
                sum = sum.add(valueFor(c));
            }
        }

        return sum;
    }

    private boolean validSubtractionPair(char c, char n) {
        String validFollowOnCharacters = validSubtractions.get(c);
        return validFollowOnCharacters != null && validFollowOnCharacters.indexOf(n) >= 0;
    }

    private char next(String number, int index) {
        int nextIndex = index + 1;
        return inString(nextIndex, number) ? Character.toUpperCase(number.charAt(nextIndex)) : 0;
    }

    private char current(String number, int i) {
        return Character.toUpperCase(number.charAt(i));
    }

    private boolean nextBigger(char c, char n) {
        return n != 0 && currentLessThanNext(c, n);
    }

    private boolean currentLessThanNext(char c, char n) {
        return valueFor(c).compareTo(valueFor(n)) < 0;
    }

    private boolean inString(int index, String value) {
        return value != null && index < value.length();
    }

    private BigDecimal valueFor(char c) {
        BigDecimal result =  digitToValue.get(Character.toUpperCase(c));

        if(result == null) {
            throw new IllegalArgumentException("Invalid character: "  + c);
        }

        return result;
    }

}
