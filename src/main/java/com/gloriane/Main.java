package com.gloriane;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Pet> pets = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        loadPetsFromFile();

        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Pet Management System");
            System.out.println("1. Register Pets");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Store Data");
            System.out.println("4. Display Records");
            System.out.println("5. Generate Reports");
            System.out.println("6. Exit");
            System.out.print("Please select an option: ");

            int choice = new Scanner(System.in).nextInt();
            try {
                switch (choice) {
                    case 1:
                        registerPet();
                        break;
                    case 2:
                        scheduleAppointment();
                        break;
                    case 3:
                        storeData();
                        break;
                    case 4:
                        displayRecords();
                        break;
                    case 5:
                        generateReports();
                        break;
                    case 6:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    public static void registerPet() {
        System.out.println("Enter pet name: ");
        String name = new Scanner(System.in).nextLine();
        if (pets.stream().anyMatch(p -> p.getName().equalsIgnoreCase(name))) {
            System.out.println("Pet already registered with that name.");
            return;
        }
        System.out.println("Enter pet breed: ");
        String breed = new Scanner(System.in).nextLine();

        System.out.println("Enter pet age: ");
        int age = new Scanner(System.in).nextInt();

        System.out.println("Enter owner name: ");
        String ownerName = new Scanner(System.in).nextLine();

        System.out.println("Enter contact info: ");
        String contactInfo = new Scanner(System.in).nextLine();

        Pet newPet = new Pet(name, breed, age, ownerName, contactInfo, new ArrayList<>());
        pets.add(newPet);
        savePetsToFile();
        System.out.println("Pet registered successfully!");
    }

    public static void scheduleAppointment() {
        if (pets.isEmpty()) {
            System.out.println("No pets registered. Please register a pet first.");
            return;
        }
        System.out.println("Scheduling appointment...");
        System.out.println("Enter appointment type: ");
        String appointmentType = new Scanner(System.in).nextLine();
        System.out.println("Enter appointment date/time: ");
        String appointmentDateTime = new Scanner(System.in).nextLine();
        System.out.println("Enter appointment notes: ");
        String appointmentNotes = new Scanner(System.in).nextLine();
        Appointment appointment = new Appointment(1, appointmentType, appointmentDateTime, appointmentNotes);
        appointments.add(appointment);
        savePetsToFile();
    }

    public static void storeData() {
        System.out.println("Storing data...");
        savePetsToFile();
    }

    private static void savePetsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pet.ser"))) {
            oos.writeObject(pets);
            oos.writeObject(appointments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadPetsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pet.ser"))) {
            pets = (List<Pet>) ois.readObject();
            appointments = (List<Appointment>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
