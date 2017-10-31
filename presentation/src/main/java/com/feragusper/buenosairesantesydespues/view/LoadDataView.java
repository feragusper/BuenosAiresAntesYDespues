package com.feragusper.buenosairesantesydespues.view;

import android.content.Context;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Interface representing a View that will use to load data.
 */
interface LoadDataView {
    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hide a loading view.
     */
    void hideLoading();

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    void showError(String message);

    /**
     * Get a {@link Context}.
     */
    Context getContext();
}
