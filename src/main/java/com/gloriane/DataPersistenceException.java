package com.gloriane;

/**
 * Exception thrown when data cannot be saved to or loaded from storage.
 */
public class DataPersistenceException extends PetCareException {

    /**
     * Constructs a new DataPersistenceException with the specified detail message.
     *
     * @param message the detail message describing the persistence failure
     */
    public DataPersistenceException(String message) {
        super(message);
    }

    /**
     * Constructs a new DataPersistenceException with the specified detail message and cause.
     *
     * @param message the detail message describing the persistence failure
     * @param cause   the underlying IOException or other cause
     */
    public DataPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}

