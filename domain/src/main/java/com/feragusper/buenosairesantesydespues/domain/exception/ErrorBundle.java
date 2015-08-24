package com.feragusper.buenosairesantesydespues.domain.exception;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Interface to represent a wrapper around an {@link Exception} to manage errors.
 */
public interface ErrorBundle {
  Exception getException();

  String getErrorMessage();
}
