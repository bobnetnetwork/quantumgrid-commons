package network.bobnet.quantumgrid.commons.exceptions;

/**
 * Exception that is thrown when a problem occurs during patch application.
 */
public class PatchApplicationException extends AbstractApplicationException {

    /**
     * Constructs a new patch application exception with the specified detail message
     * and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public PatchApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
