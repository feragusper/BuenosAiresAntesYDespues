package com.feragusper.buenosairesantesydespues.exception;

import com.feragusper.buenosairesantesydespues.domain.exception.ErrorBundle;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Wrapper around Exceptions used to manage errors in the repository.
 */
public class RepositoryErrorBundle implements ErrorBundle {

    private final Exception exception;

    public RepositoryErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        String message = "";
        if (this.exception != null) {
            this.exception.getMessage();
        }
        return message;
    }
}
