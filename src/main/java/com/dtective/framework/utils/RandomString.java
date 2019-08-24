package com.dtective.framework.utils;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class RandomString {

    public static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER = UPPER.toLowerCase(Locale.ROOT);
    public static final String DIGITS = "0123456789";
    public static final String ALPHANUM = UPPER + LOWER + DIGITS;
    private static String lastRandomString = "";
    private final Random random;
    private final char[] symbols;
    private final char[] buf;

    public RandomString(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Create an alphanumeric string generator.
     */
    public RandomString(int length, Random random) {
        this(length, random, ALPHANUM);
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     */
    public RandomString(int length) {
        this(length, new SecureRandom());
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     */
    public RandomString(int length, String symbols) {
        this(length, new SecureRandom(), symbols);
    }

    public static String getLastRandomString() {
        return lastRandomString;
    }

    /**
     * Generate a random string.
     */
    public String nextString() {
        final int bound = 254;

        if (symbols.equals("ASCII")) {
            for (int idx = 0; idx < buf.length; ++idx)
                buf[idx] = (char) random.nextInt(bound);
        } else {
            for (int idx = 0; idx < buf.length; ++idx)
                buf[idx] = symbols[random.nextInt(symbols.length)];
        }

        return lastRandomString = new String(buf);
    }


}
