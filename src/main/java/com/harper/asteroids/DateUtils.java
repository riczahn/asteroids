package com.harper.asteroids;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

public class DateUtils {

    public static LocalDate convertMillisToLocalDate(Long milliseconds) {
        return Instant.ofEpochMilli(milliseconds).atOffset(UTC).toLocalDate();
    }

    public static boolean isDateWithinThisWeek(LocalDate date) {
        var thisWeeksMonday = getCurrentDate().with(DayOfWeek.MONDAY);
        var thisWeeksSunday = getCurrentDate().with(DayOfWeek.SUNDAY);

        return date.isAfter(thisWeeksMonday) && date.isBefore(thisWeeksSunday);
    }

    public static LocalDate getCurrentDate() {
        return Instant.now().atOffset(UTC).toLocalDate();
    }
}
