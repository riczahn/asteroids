package com.harper.asteroids;

import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DateUtilsTest {

    @Test
    public void givenMillis__whenConvertingToLocalDate__thenReturnsCorrectLocalDateForUTC() {
        long millis = 691733640000L;
        LocalDate expectedDate = LocalDate.of(1991, 12, 3);

        var actualDate = DateUtils.convertMillisToLocalDate(millis);

        assertThat(expectedDate.isEqual(actualDate), is(true));
    }

    @Test
    public void givenMillis__whenConvertingToLocalDate__thenReturnsCorrectLocalDateForUTC_2() {
        long millis = 1188026760000L;
        LocalDate expectedDate = LocalDate.of(2007, 8, 25);

        var actualDate = DateUtils.convertMillisToLocalDate(millis);

        assertThat(expectedDate.isEqual(actualDate), is(true));
    }

    @Test
    public void givenMillis__whenConvertingToLocalDate__thenReturnsCorrectLocalDateForUTC_3() {
        long millis = 1692438000000L;
        LocalDate expectedDate = LocalDate.of(2023, 8, 19);

        var actualDate = DateUtils.convertMillisToLocalDate(millis);

        assertThat(expectedDate.isEqual(actualDate), is(true));
    }

    @Test
    @Ignore(value = "Deactivated as the implementation depends on the current date. Mocking will take significant amount of time.")
    public void givenASaturday__whenCheckingIfDateIsWithinThisWeek__thenReturnTrue() {
        var saturday = LocalDate.of(2023, 8, 19);

        var result = DateUtils.isDateWithinThisWeek(saturday);

        assertThat(result, is(true));
    }
}