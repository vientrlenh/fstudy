package com.vsoft.fstudy.shared;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DateMapper {
    
    private DateMapper() {}

    private static final List<String> ACCEPTED_LOCALDATE_FORMATS = List.of(
        "dd-MM-yyyy", 
        "dd/MM/yyyy", 
        "dd.MM.yyyy"
    );

    private static final List<DateTimeFormatter> ACCEPTED_LOCALDATE_FORMATTERS = ACCEPTED_LOCALDATE_FORMATS.stream().map(DateTimeFormatter::ofPattern).toList();

    public static LocalDate toLocalDate(String localDateStr) {
        if (localDateStr == null || localDateStr.isBlank()) {
            return null;
        }
        String stripped = localDateStr.strip();
        for (DateTimeFormatter formatter : ACCEPTED_LOCALDATE_FORMATTERS) {
            try {
                return LocalDate.parse(stripped, formatter);
            } catch (DateTimeParseException _) {}
        }
        throw new IllegalArgumentException("Invalid requested date format.");
    }
}
