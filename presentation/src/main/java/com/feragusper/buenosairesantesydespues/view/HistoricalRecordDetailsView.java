package com.feragusper.buenosairesantesydespues.view;

import com.feragusper.buenosairesantesydespues.model.HistoricalRecordModel;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * <p>
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a user profile.
 */
public interface HistoricalRecordDetailsView extends LoadDataView {
    /**
     * Render a historicalRecord in the UI.
     *
     * @param historicalRecord The {@link HistoricalRecordModel} that will be shown.
     */
    void renderHistoricalRecord(HistoricalRecordModel historicalRecord);
}
