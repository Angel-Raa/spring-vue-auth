package io.github.angel.raa.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

import lombok.NonNull;

public class Slugify {
    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern SEPARATORS = Pattern.compile("[\\s\\p{Punct}&&[^-]]");

    public static @NonNull String slugify(String text) {
        String noseparators = SEPARATORS.matcher(text).replaceAll("-");
        String normalized = Normalizer.normalize(noseparators, Normalizer.Form.NFD);
        String slug = NON_LATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase().replaceAll("-{2,}", "-").replaceAll("^-|-$", "");

    }

}
