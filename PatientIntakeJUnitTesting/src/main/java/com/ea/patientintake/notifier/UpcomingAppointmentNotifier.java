package com.ea.patientintake.notifier;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.ea.patientintake.model.PatientAppointment;
import com.ea.patientintake.service.ClinicCalendar;

public class UpcomingAppointmentNotifier {

  private ClinicCalendar calendar;
  private EmailNotifier notifier;

  public UpcomingAppointmentNotifier(ClinicCalendar calendar, EmailNotifier notifier) {
    this.notifier = notifier;
    this.calendar = calendar;
  }

  public void run() {
    calendar
        .getTomorrowAppointments()
        .forEach(
            appt -> {
              sendNotificationForAppointment(appt);
            });
  }

  private void sendNotificationForAppointment(PatientAppointment appt) {
    String email =
        appt.getPatientLastName().toLowerCase()
            + appt.getPatientFirstName().toLowerCase()
            + "@mail.com";
    System.out.println("Sending with body: " + buildMessageBody(appt));
    notifier.sendNotification("Appointment Reminder", buildMessageBody(appt), email);
  }

  private String buildMessageBody(PatientAppointment appt) {
    return "You have an appointment tomorrow at "
        + appt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("h:mm a", Locale.US))
        + " with Dr. "
        + appt.getDoctor().getName()
        + ".";
  }
}
