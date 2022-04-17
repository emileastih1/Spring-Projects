package com.ea.patientintake.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ea.patientintake.model.Doctor;
import com.ea.patientintake.model.PatientAppointment;
import com.ea.patientintake.util.DateTimeConverter;

public class ClinicCalendar {

  private List<PatientAppointment> appointments;
  private LocalDate today;

  public ClinicCalendar(LocalDate today) {
    this.today = today;
    this.appointments = new ArrayList<>();
  }

  public void addAppointment(
      String patientFirstName, String patientLastName, String doctorKey, String dateTime) {
    Doctor doc = Doctor.valueOf(doctorKey.toLowerCase());
    LocalDateTime localDateTime = DateTimeConverter.convertStringToDateTime(dateTime, today);
    PatientAppointment appointment =
        new PatientAppointment(patientFirstName, patientLastName, localDateTime, doc);
    appointments.add(appointment);
  }

  public List<PatientAppointment> getAppointments() {
    return this.appointments;
  }

  public List<PatientAppointment> getTodayAppointments() {
    return appointments
        .stream()
        .filter(appt -> appt.getAppointmentDateTime().toLocalDate().equals(today))
        .collect(Collectors.toList());
  }

  public List<PatientAppointment> getTomorrowAppointments() {
    LocalDate tomorrow = today.plusDays(1);
    return getAppointmentsForDate(tomorrow);
  }

  public boolean hasAppointment(LocalDate date) {
    return appointments
        .stream()
        .anyMatch(appt -> appt.getAppointmentDateTime().toLocalDate().equals(date));
  }

  private List<PatientAppointment> getAppointmentsForDate(LocalDate tomorrow) {
    return appointments
        .stream()
        .filter(appt -> appt.getAppointmentDateTime().toLocalDate().equals(tomorrow))
        .collect(Collectors.toList());
  }

  public List<PatientAppointment> getUpcomingAppointments() {
    return appointments
        .stream()
        .filter(app -> app.getAppointmentDateTime().toLocalDate().isAfter(today))
        .collect(Collectors.toList());
  }
}
