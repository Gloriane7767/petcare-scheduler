# Pet Care Scheduler

A Java desktop application for managing pet records and scheduling appointments.

## Features

- Add, update, and remove pets
- Schedule, view, and cancel appointments
- Store application data in serialized files
- Validate pet and appointment information
- Display helpful error messages for invalid operations

## Project structure

```text
src/main/java/com/gloriane/
├── Main.java                         # Application entry point
├── Pet.java                          # Pet data model
├── Appointment.java                  # Appointment data model
└── *Exception.java                   # Domain and persistence exceptions
pom.xml                               # Maven configuration
pet.ser                               # Saved serialized pet data
```

## Requirements

- Java 25 or a compatible JDK
- Apache Maven

## Run the application

Clone the repository and compile it:

```bash
git clone https://github.com/Gloriane7767/petcare-scheduler.git
cd petcare-scheduler
mvn compile
```

Run the main class:

```bash
mvn exec:java -Dexec.mainClass="com.gloriane.Main"
```

Alternatively, open the project in IntelliJ IDEA or another Java IDE and run `src/main/java/com/gloriane/Main.java`.

## Build

Create a compiled Maven package with:

```bash
mvn package
```

## Data

The application uses serialization to persist its pet data in `pet.ser`. Keep this file with the application so existing records remain available between runs.
