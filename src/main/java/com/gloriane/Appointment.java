package com.gloriane;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment implements Serializable {
    private String appointmentType;
    private LocalDateTime dateTime;
    private String notes;

    public Appointment(String appointmentType, LocalDateTime dateTime, String notes) {
        this.appointmentType = appointmentType;
        this.dateTime = dateTime;
        this.notes = notes;
    }

    public String getAppointmentType() { return appointmentType; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getNotes() { return notes; }

    public void setAppointmentType(String appointmentType) { this.appointmentType = appointmentType; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Appointment{" +
                "appointmentType='" + appointmentType + '\'' +
                ", dateTime='" + dateTime.format(formatter) + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
