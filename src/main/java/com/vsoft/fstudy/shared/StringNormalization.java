package com.vsoft.fstudy.shared;

import java.util.Locale;

public class StringNormalization {

    private StringNormalization() {}
    
    public static String normalizeEmail(String email) {
        if (email == null) {
            return null;
        }
        return email.strip().toLowerCase(Locale.ROOT);
    } 

    public static String normalizePhone(String phone) {
        if (phone == null) {
            return null;
        }
        return phone.strip().replaceAll("[\\s.-]", "");
    }

    public static String trimAndCollapseSpace(String input) {
        if (input == null) {
            return null;
        }
        return input.strip().replaceAll("\\s+", " ");
    }
}
