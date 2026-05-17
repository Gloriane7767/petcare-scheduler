package com.gloriane;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Pet> pets = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();

    static void main(String[] args) {
        loadPetsFromFile();

        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Pet Management System");
            System.out.println("1. Register Pets");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Store Data");
            System.out.println("4. Display Records");
            System.out.println("5. Generate Reports");
            System.out.println("6. Save & Exit");
            System.out.println("7. Exit without saving");
            System.out.print("Please select an option: ");

            Scanner in = new Scanner(System.in);
            String choiceLine = in.nextLine().trim();
            int choice = -1;
            try {
                if (!choiceLine.isEmpty()) {
                    choice = Integer.parseInt(choiceLine);
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

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
                        // Save and then exit
                        try {
                            savePetsToFile();
                            System.out.println("Data saved. Exiting.");
                        } catch (IOException e) {
                            System.out.println("Failed to save data before exit: " + e.getMessage());
                        }
                        running = false;
                        break;
                    case 7:
                        // Exit without saving
                        System.out.println("Exiting without saving.");
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

    private static void registerPet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Registering a new pet...");

        System.out.print("Enter pet name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) { System.out.println("Name cannot be empty."); return; }
        if (pets.stream().anyMatch(p -> p.getName().equalsIgnoreCase(name))) {
            System.out.println("Pet already registered with that name.");
            return;
        }

        System.out.print("Enter pet breed: ");
        String breed = scanner.nextLine().trim();
        if (breed.isEmpty()) { System.out.println("Breed cannot be empty."); return; }

        System.out.print("Enter pet age: ");
        String ageInput = scanner.nextLine().trim();
        if (!ageInput.matches("\\d+")) { System.out.println("Age must be a positive number."); return; }
        int age = Integer.parseInt(ageInput);
        if (age <= 0) { System.out.println("Age must be greater than zero."); return; }

        System.out.print("Enter owner name: ");
        String ownerName = scanner.nextLine().trim();
        if (ownerName.isEmpty()) { System.out.println("Owner name cannot be empty."); return; }

        System.out.print("Enter contact info: ");
        String contactInfo = scanner.nextLine().trim();
        if (contactInfo.isEmpty()) { System.out.println("Contact info cannot be empty."); return; }

        Pet newPet = new Pet(name, breed, age, ownerName, contactInfo, new ArrayList<>());
        pets.add(newPet);
        try {
            savePetsToFile();
            System.out.println("Pet registered successfully!");
        } catch (IOException e) {
            System.out.println("Failed to save pet data: " + e.getMessage());
        }
    }

    private static void scheduleAppointment() {
        if (pets.isEmpty()) {
            System.out.println("No pets registered. Please register a pet first.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scheduling appointment...");

        System.out.println("Select a pet:");
        for (int i = 0; i < pets.size(); i++) {
            System.out.println((i + 1) + ". " + pets.get(i).getName());
        }
        System.out.print("Enter pet number: ");
        String petIndexInput = scanner.nextLine().trim();
        if (!petIndexInput.matches("\\d+")) { System.out.println("Invalid selection."); return; }
        int petIndex = Integer.parseInt(petIndexInput) - 1;
        if (petIndex < 0 || petIndex >= pets.size()) { System.out.println("Invalid pet selection."); return; }
        Pet selectedPet = pets.get(petIndex);

        System.out.print("Enter appointment type: ");
        String appointmentType = scanner.nextLine().trim();
        if (appointmentType.isEmpty()) { System.out.println("Appointment type cannot be empty."); return; }

        System.out.print("Enter appointment date/time (yyyy-MM-dd HH:mm): ");
        String appointmentDateTime = scanner.nextLine().trim();
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(appointmentDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format. Please use yyyy-MM-dd HH:mm.");
            return;
        }

        // Require appointment to be in the future
        if (dateTime.isBefore(LocalDateTime.now())) {
            System.out.println("Appointment date/time must be in the future.");
            return;
        }

        System.out.print("Enter appointment notes: ");
        String appointmentNotes = scanner.nextLine().trim();
        if (appointmentNotes.isEmpty()) { System.out.println("Notes cannot be empty."); return; }

        Appointment appointment = new Appointment(appointmentType, dateTime, appointmentNotes);
        selectedPet.addAppointment(appointment);
        appointments.add(appointment);
        try {
            savePetsToFile();
            System.out.println("Appointment scheduled successfully for " + selectedPet.getName() + "!\n--- Appointment details ---");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            System.out.println("Type: " + appointment.getAppointmentType());
            System.out.println("Date/Time: " + appointment.getDateTime().format(formatter));
            System.out.println("Notes: " + appointment.getNotes());
            System.out.println("Total appointments for " + selectedPet.getName() + ": " + selectedPet.getAppointments().size());
        } catch (IOException e) {
            System.out.println("Failed to save appointment data: " + e.getMessage());
        }
    }

    private static void storeData() {
        System.out.println("Storing data...");
        System.out.println("Data stored successfully!");
        try {
            savePetsToFile();
            System.out.println("Data persisted to disk.");
        } catch (IOException e) {
            System.out.println("Failed to store data: " + e.getMessage());
        }
    }

    private static void displayRecords() {
        System.out.println("Displaying records...");
        System.out.println("Registered Pets:");
        if (pets.isEmpty()) {
            System.out.println("No pets registered.");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (Pet pet : pets) {
            System.out.println("- Pet: " + pet.getName() + " (" + pet.getBreed() + ", age " + pet.getAge() + ")");
            System.out.println("  Owner: " + pet.getOwnerName() + ", Contact: " + pet.getContactInfo());
            System.out.println("  Registered: " + pet.getRegistrationDateTime().format(formatter));
            List<Appointment> appts = pet.getAppointments();
            if (appts == null || appts.isEmpty()) {
                System.out.println("  Appointments: none");
            } else {
                System.out.println("  Appointments:");
                for (Appointment a : appts) {
                    System.out.println("    - " + a.getAppointmentType() + " at " + a.getDateTime().format(formatter) + " — " + a.getNotes());
                }
            }
            System.out.println();
        }
    }

    private static void generateReports() {
        System.out.println("Generating reports...");
        System.out.println("Total number of pets: " + pets.size());
        System.out.println("Total number of appointments: " + appointments.size());
    }

    private static void savePetsToFile() throws IOException {
        // Ensure we have something to save
        if (pets == null || pets.isEmpty()) {
            throw new IOException("No pets to save. Register at least one pet before saving.");
        }

        // 1) Binary serialization (existing behavior)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pet.ser"))) {
            oos.writeObject(pets);
            oos.writeObject(appointments);
        } catch (IOException e) {
            throw new IOException("Failed to write binary data: " + e.getMessage(), e);
        }

        // 2) Human-readable flat files (CSV)
        try {
            savePetsToCSV("pets.csv");
            saveAppointmentsToCSV("appointments.csv");
        } catch (IOException e) {
            throw new IOException("Failed to write flat files: " + e.getMessage(), e);
        }
    }

    private static void savePetsToCSV(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        List<String> lines = new ArrayList<>();

        // Header
        lines.add("petId,name,breed,age,ownerName,contactInfo,registrationDateTime");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (Pet pet : pets) {
            String line = String.format("%s,%s,%s,%d,%s,%s,%s",
                    escapeCsv(pet.getId()),
                    escapeCsv(pet.getName()),
                    escapeCsv(pet.getBreed()),
                    pet.getAge(),
                    escapeCsv(pet.getOwnerName()),
                    escapeCsv(pet.getContactInfo()),
                    pet.getRegistrationDateTime().format(formatter));
            lines.add(line);
        }
        Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static void saveAppointmentsToCSV(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        List<String> lines = new ArrayList<>();
        // Header
        lines.add("petId,appointmentType,dateTime,notes");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (Pet pet : pets) {
            if (pet.getAppointments() == null) continue;
            for (Appointment appt : pet.getAppointments()) {
                String line = String.format("%s,%s,%s,%s",
                        escapeCsv(pet.getId()),
                        escapeCsv(appt.getAppointmentType()),
                        appt.getDateTime().format(formatter),
                        escapeCsv(appt.getNotes()));
                lines.add(line);
            }
        }
        Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static String escapeCsv(String value) {
        if (value == null) return "";
        String v = value.replace("\"", "\"\"");
        if (v.contains(",") || v.contains("\n") || v.contains("\"")) {
            return "\"" + v + "\"";
        }
        return v;
    }

    private static void loadPetsFromFile() {
        Path serPath = Paths.get("pet.ser");
        if (!Files.exists(serPath)) {
            // No saved data yet; not an error
            System.out.println("No existing data file found. Starting with empty records.");
            pets = new ArrayList<>();
            appointments = new ArrayList<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serPath.toFile()))) {
            Object obj1 = ois.readObject();
            if (obj1 instanceof List) {
                pets = (List<Pet>) obj1;
            } else {
                pets = new ArrayList<>();
            }

            Object obj2 = null;
            try {
                obj2 = ois.readObject();
            } catch (EOFException eof) {
                // older file that only stored pets
            }

            if (obj2 instanceof List) {
                appointments = (List<Appointment>) obj2;
            } else {
                // If appointments weren't serialized separately, rebuild from pet appointments
                appointments = new ArrayList<>();
                for (Pet p : pets) {
                    if (p.getAppointments() != null) appointments.addAll(p.getAppointments());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load saved data: " + e.getMessage());
            pets = new ArrayList<>();
            appointments = new ArrayList<>();
        }
    }
}
