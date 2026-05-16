package com.gloriane;

import java.util.List;

public class Pet {
    private int id;
    private String name;
    private String Breed;
    private int age;
    private String ownerName;
    private String contactInfo;
    private String RegistrationDate;
    private List<String> appointments;

    public Pet(int id, String name, String breed, int age, String ownerName, String contactInfo, String registrationDate, List<String> appointments) {
        this.id = id;
        this.name = name;
        Breed = breed;
        this.age = age;
        this.ownerName = ownerName;
        this.contactInfo = contactInfo;
        RegistrationDate = registrationDate;
        this.appointments = appointments;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return Breed;
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

    public String getRegistrationDate() {
        return RegistrationDate;
    }

    public List<String> getAppointments() {
        return appointments;
    }

    public void setBreed(String breed) {
        Breed = breed;
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

    public void setRegistrationDate(String registrationDate) {
        RegistrationDate = registrationDate;
    }

    public void setAppointments(List<String> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Breed='" + Breed + '\'' +
                ", age=" + age +
                ", ownerName='" + ownerName + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", RegistrationDate='" + RegistrationDate + '\'' +
                ", appointments=" + appointments +
                '}';
    }
}
