package com.harper.asteroids;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DateUtilsTest {

    private DateUtils dateUtils;

    @Before
    public void setUp() {
        // we have to mock the current date but want the rest to be like the real implementation
        dateUtils = mock(DateUtils.class);
        when(dateUtils.isDateWithinThisWeek(any())).thenCallRealMethod();
        when(dateUtils.convertMillisToLocalDate(any())).thenCallRealMethod();
    }

    @Test
    public void givenMillis__whenConvertingToLocalDate__thenReturnsCorrectLocalDateForUTC() {
        long millis = 691733640000L;
        LocalDate expectedDate = LocalDate.of(1991, 12, 3);

        var actualDate = dateUtils.convertMillisToLocalDate(millis);

        assertThat(expectedDate.isEqual(actualDate), is(true));
    }

    @Test
    public void givenMillis__whenConvertingToLocalDate__thenReturnsCorrectLocalDateForUTC_2() {
        long millis = 1188026760000L;
        LocalDate expectedDate = LocalDate.of(2007, 8, 25);

        var actualDate = dateUtils.convertMillisToLocalDate(millis);

        assertThat(expectedDate.isEqual(actualDate), is(true));
    }

    @Test
    public void givenMillis__whenConvertingToLocalDate__thenReturnsCorrectLocalDateForUTC_3() {
        long millis = 1692438000000L;
        LocalDate expectedDate = LocalDate.of(2023, 8, 19);

        var actualDate = dateUtils.convertMillisToLocalDate(millis);

        assertThat(expectedDate.isEqual(actualDate), is(true));
    }

    @Test
    public void givenItIsFriday__whenCheckingIfTheNextDayIsWithinThisWeek__thenReturnTrue() {
        // given
        var friday = LocalDate.of(2023, 8, 18);
        when(dateUtils.getCurrentDate()).thenReturn(friday);

        var saturday = friday.plusDays(1);

        // when
        var result = dateUtils.isDateWithinThisWeek(saturday);

        // then
        assertThat(result, is(true));
    }

    @Test
    public void givenItIsFriday__whenCheckingIfFridayNextWeekIsWithinThisWeek__thenReturnFalse() {
        // given
        var friday = LocalDate.of(2023, 8, 18);
        when(dateUtils.getCurrentDate()).thenReturn(friday);

        var fridayNextWeek = friday.plusWeeks(1);

        // when
        var result = dateUtils.isDateWithinThisWeek(fridayNextWeek);

        // then
        assertThat(result, is(false));
    }
}