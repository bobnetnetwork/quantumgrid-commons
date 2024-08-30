package network.bobnet.quantumgrid.commons.exceptions;

/**
 * The abstract base class for all application-specific exceptions.
 */
public abstract class AbstractApplicationException extends RuntimeException {

    /**
     * Constructs a new application exception with {@code null} as its detail message.
     */
    protected AbstractApplicationException() {
        super();
    }

    /**
     * Constructs a new application exception with the specified detail message.
     *
     * @param message the detail message
     */
    protected AbstractApplicationException(String message) {
        super(message);
    }

    /**
     * Constructs a new application exception with the specified detail message
     * and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    protected AbstractApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
