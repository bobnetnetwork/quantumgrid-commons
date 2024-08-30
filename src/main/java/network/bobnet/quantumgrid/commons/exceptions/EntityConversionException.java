package network.bobnet.quantumgrid.commons.exceptions;

/**
 * Exception that is thrown when a problem occurs during entity conversion.
 */
public class EntityConversionException extends AbstractApplicationException {

    /**
     * Constructs a new entity conversion exception with the specified detail message
     * and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public EntityConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}

