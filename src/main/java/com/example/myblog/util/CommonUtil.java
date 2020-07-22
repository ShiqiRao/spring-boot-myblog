package com.example.myblog.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommonUtil {

    private static final DateTimeFormatter englishDateFormatter;
    private static final Map<Long, String> daysLookup;

    public static String format(LocalDateTime localDateTime) {
        return localDateTime.format(englishDateFormatter);
    }

    public static String toSlug(String title) {
        return String.join("-", title.toLowerCase()
                .replace("\n", " ")
                .replace("[^a-z\\d\\s]", " ")
                .split(" "))
                .replace("-+", "-");
    }

    static {
        daysLookup = buildDaysLookup();
        englishDateFormatter = new DateTimeFormatterBuilder()
                .appendPattern("MM,dd")
                .appendText(ChronoField.DAY_OF_MONTH, daysLookup)
                .appendLiteral(",")
                .appendPattern("yyyy")
                .toFormatter(Locale.ENGLISH);
    }

    private static Map<Long, String> buildDaysLookup() {
        Map<Long, String> ret = new HashMap<>();
        for (int i = 1; i <= 31; i++) {
            ret.put((long) i, getDayOfMonthSuffix(i));
        }
        return ret;
    }

    private static String getDayOfMonthSuffix(int n) {
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }


}
