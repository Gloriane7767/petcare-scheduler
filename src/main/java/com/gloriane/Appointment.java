package com.gloriane;

public class Appointment {
    private String AppointmentType;
    private String dateTime;
    private String Notes;

    public Appointment(String appointmentType, String dateTime, String notes) {
        AppointmentType = appointmentType;
        this.dateTime = dateTime;
        Notes = notes;
    }

    public String getAppointmentType() {
        return AppointmentType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getNotes() {
        return Notes;
    }

        public void setAppointmentType(String appointmentType) {
            AppointmentType = appointmentType;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public void setNotes(String notes) {
            Notes = notes;
        }

    @Override
    public String toString() {
        return "Appointment{" +
                "AppointmentType='" + AppointmentType + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", Notes='" + Notes + '\'' +
                '}';
    }
}
