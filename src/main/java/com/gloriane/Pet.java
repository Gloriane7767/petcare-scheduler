package com.gloriane;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pet implements Serializable {
    private String id;
    private String name;
    private String breed;
    private int age;
    private String ownerName;
    private String contactInfo;
    private LocalDateTime registrationDateTime;
    private List<Appointment> appointments;

    public Pet(String name, String breed, int age, String ownerName, String contactInfo, List<Appointment> appointments) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.ownerName = ownerName;
        this.contactInfo = contactInfo;
        this.registrationDateTime = LocalDateTime.now();
        this.appointments = appointments != null ? appointments : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public LocalDateTime getRegistrationDateTime() {
        return registrationDateTime;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDateTime = registrationDate;
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }
}
