package com.feragusper.buenosairesantesydespues.di;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Interface representing a contract for clients that contains a component for dependency injection.
 */
public interface HasComponent<C> {
    C getComponent();
}
