package com.ea.patientintake.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("dateTime")
@DisplayName("DateTimeConverted should")
class DateTimeConverterTest {

  private static LocalDate todayLocalDate;
  private static LocalDateTime todayLocalDateTime;

  @BeforeAll
  static void init() {
    todayLocalDate = LocalDate.of(2022, 4, 15);
    todayLocalDateTime = LocalDateTime.of(2022, 4, 15, 4, 0);
  }

  @Nested
  @DisplayName("Convert string with 'today' keyword")
  class TodayTests {
    @Test
    @DisplayName("correctly")
    void convertTodayStringCorrectly() {
      LocalDateTime result =
          DateTimeConverter.convertStringToDateTime("today 4:00 am", todayLocalDate);
      assertEquals(
          result,
          todayLocalDateTime,
          () ->
              "Fialed to convert 'today' string to expected date time,"
                  + "today passed was: "
                  + todayLocalDate);
    }

    @Test
    @DisplayName("correctly regardless of case")
    void convertTodayStringCorrectlyCaseInsensitive() {
      LocalDateTime result =
          DateTimeConverter.convertStringToDateTime("toDay 4:00 am", todayLocalDate);
      assertEquals(
          result,
          todayLocalDateTime,
          () ->
              "Fialed to convert 'today' string to expected date time,"
                  + "today passed was: "
                  + todayLocalDate);
    }
  }

  @Test
  @DisplayName("Throw exception if the date string passed has an incorrect pattern")
  void throwExceptionIfIncorrectPatternProvided() {
    RuntimeException error =
        assertThrows(
            RuntimeException.class,
            () -> DateTimeConverter.convertStringToDateTime("4/15/2022 400 am", todayLocalDate));

    assertEquals(
        "Unable to create date time from: [4/15/2022 400 am], please enter with format [M/d/yyyy h:mm a],"
            + " Text '4/15/2022 400 AM' could not be parsed at index 13",
        error.getMessage());
  }
}
