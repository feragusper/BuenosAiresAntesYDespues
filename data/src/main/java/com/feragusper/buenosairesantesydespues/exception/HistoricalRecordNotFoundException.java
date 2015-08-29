package com.feragusper.buenosairesantesydespues.exception;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Exception throw by the application when a HistoricalRecord search can't return a valid result.
 */
public class HistoricalRecordNotFoundException extends Exception {

    public HistoricalRecordNotFoundException() {
        super();
    }

    public HistoricalRecordNotFoundException(final String message) {
        super(message);
    }

    public HistoricalRecordNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public HistoricalRecordNotFoundException(final Throwable cause) {
        super(cause);
    }
}
