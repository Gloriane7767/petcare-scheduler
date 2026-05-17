package com.gloriane;

/**
 * Exception thrown when pet registration data is invalid or incomplete.
 */
public class InvalidPetDataException extends PetCareException {

    /**
     * Constructs a new InvalidPetDataException with the specified detail message.
     *
     * @param message the detail message describing which pet data is invalid
     */
    public InvalidPetDataException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidPetDataException with the specified detail message and cause.
     *
     * @param message the detail message describing which pet data is invalid
     * @param cause   the underlying cause
     */
    public InvalidPetDataException(String message, Throwable cause) {
        super(message, cause);
    }
}

