package network.bobnet.quantumgrid.commons.exceptions;

/**
 * Exception that is thrown when a resource is not found.
 */
public class NotFoundException extends AbstractApplicationException {

    /**
     * Constructs a new not-found exception with {@code null} as its detail message.
     */
    public NotFoundException() {
        super();
    }

    /**
     * Constructs a new not-found exception with the specified detail message.
     *
     * @param message the detail message
     */
    public NotFoundException(final String message) {
        super(message);
    }

}
