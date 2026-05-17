package com.gloriane;

/**
 * Exception thrown when a pet with the same name already exists in the system.
 */
public class PetAlreadyExistsException extends PetCareException {

    /**
     * Constructs a new PetAlreadyExistsException for a duplicate pet name.
     *
     * @param petName the name of the pet that already exists
     */
    public PetAlreadyExistsException(String petName) {
        super("Pet with name '" + petName + "' is already registered.");
    }

    /**
     * Constructs a new PetAlreadyExistsException with a custom message.
     *
     * @param message the detail message
     */
    public PetAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

