package com.ea.patientintake.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.ea.patientintake.model.Doctor;
import com.ea.patientintake.model.PatientAppointment;

import lombok.extern.java.Log;

@Log
class ClinicCalendarTest {

  ClinicCalendar calendar;

  @BeforeAll
  static void testClassSetup() {
    log.info("Before all ....");
  }

  @BeforeEach
  void init() {
    log.info("Before each ....");
    calendar = new ClinicCalendar(LocalDate.of(2022, 4, 15));
  }

  @Nested
  @DisplayName("return upcoming appointments")
  class UpcomingAppointments {

    @Test
    @DisplayName("When they are done")
    void whenThereAreNone() {
      List<PatientAppointment> appointments = calendar.getUpcomingAppointments();
      assertEquals(0, appointments.size());
    }

    @Test
    @DisplayName("For Tomorrow")
    void whenThereAreSomePastAndFuture() {
      calendar.addAppointment("Emile", "Astih", Doctor.avery.toString(), "9/18/1990 2:00 pm");
      calendar.addAppointment("Layal", "Khoury", Doctor.avery.toString(), "8/11/1994 2:00 pm");
      calendar.addAppointment("Ralph", "Riachi", Doctor.avery.toString(), "9/18/2023 2:00 pm");
      assertEquals(1, calendar.getUpcomingAppointments().size());
    }
  }

  @Test
  void allowEntryOfAnAppointment() {
    log.info("entry of appointment ....");
    calendar.addAppointment("Emile", "Astih", Doctor.avery.toString(), "9/18/1990 2:00 pm");
    List<PatientAppointment> appointments = calendar.getAppointments();
    assertNotNull(appointments);
    assertEquals(1, appointments.size());

    PatientAppointment patientAppointment = appointments.get(0);

    assertAll(
        () -> assertEquals("Emile", patientAppointment.getPatientFirstName()),
        () -> assertEquals("Astih", patientAppointment.getPatientLastName()),
        () -> assertSame(Doctor.avery, patientAppointment.getDoctor()),
        () ->
            assertEquals(
                "9/18/1990 02:00 PM",
                patientAppointment
                    .getAppointmentDateTime()
                    .format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a"))));
  }

  @Test
  void returnTrueIfHasAppointmentsIfThereAreAppointments() {
    calendar.addAppointment("Layal", "Astih", Doctor.johnson.toString(), "9/18/1990 2:00 pm");
    assertTrue(calendar.hasAppointment(LocalDate.of(1990, 9, 18)));
  }

  @Test
  void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
    assertFalse(calendar.hasAppointment(LocalDate.of(1994, 8, 11)));
  }

  @Test
  @Disabled
  void returnCurrentDaysAppointments() {
    calendar.addAppointment("Jim", "Weaver", "avery", "04/15/2022 4:00 pm");
    calendar.addAppointment("Jim", "Weaver", "avery", "04/15/2022 4:00 pm");
    calendar.addAppointment("Jim", "Weaver", "avery", "04/18/2022 4:00 pm");
    assertEquals(2, calendar.getTodayAppointments().size());
    //    assertIterableEquals(calendar.getTodayAppointments(), calendar.getAppointments());
  }

  @AfterEach
  void tearDownEachTest() {
    log.info("After each ...");
  }

  @AfterAll
  static void tearDownTestClass() {
    log.info("After all ...");
  }
}
