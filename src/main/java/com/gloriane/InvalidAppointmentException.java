package com.gloriane;

/**
 * Exception thrown when an appointment cannot be scheduled due to invalid data or time.
 */
public class InvalidAppointmentException extends PetCareException {

    /**
     * Constructs a new InvalidAppointmentException with the specified detail message.
     *
     * @param message the detail message describing why the appointment is invalid
     */
    public InvalidAppointmentException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidAppointmentException with the specified detail message and cause.
     *
     * @param message the detail message describing why the appointment is invalid
     * @param cause   the underlying cause of the exception
     */
    public InvalidAppointmentException(String message, Throwable cause) {
        super(message, cause);
    }
}

