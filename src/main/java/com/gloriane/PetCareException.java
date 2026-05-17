package com.gloriane;

/**
 * Base custom exception for the Pet Care Management System.
 * Used for application-specific errors related to pet management, appointments, and data persistence.
 */
public class PetCareException extends Exception {

    /**
     * Constructs a new PetCareException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by getMessage() method)
     */
    public PetCareException(String message) {
        super(message);
    }

    /**
     * Constructs a new PetCareException with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by getMessage() method)
     * @param cause   the cause (which is saved for later retrieval by getCause() method)
     */
    public PetCareException(String message, Throwable cause) {
        super(message, cause);
    }
}

