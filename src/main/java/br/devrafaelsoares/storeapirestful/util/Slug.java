package br.devrafaelsoares.storeapirestful.util;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class Slug {

    private static final Pattern NOLATIN = Pattern.compile("[^\\w-]");

    private static final Pattern WHITESPACE = Pattern.compile("\\s");

    public static String make(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        String whitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(whitespace, Normalizer.Form.NFD);
        String slug = NOLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

}