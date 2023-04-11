package andrew.backend.app.global.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class CustomDateFormat {
    public static String localToComma(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

    public static String localDateToComma(LocalDate localDate){
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        String date = localDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        String week = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
        return date+"("+week+")";
    }
}
