package com.feragusper.buenosairesantesydespues.view;

import com.feragusper.buenosairesantesydespues.model.HistoricalRecordModel;

import java.util.Collection;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link HistoricalRecordModel}.
 */
public interface HistoricalRecordListView extends LoadDataView {
    /**
     * Render a HistoricalRecord list in the UI.
     *
     * @param historicalRecordModelCollection The collection of {@link HistoricalRecordModel} that will be shown.
     */
    void renderHistoricalRecordList(Collection<HistoricalRecordModel> historicalRecordModelCollection);

    /**
     * View a {@link HistoricalRecordModel} profile/details.
     *
     * @param historicalRecordModel The historicalRecord that will be shown.
     */
    void viewHistoricalRecord(HistoricalRecordModel historicalRecordModel);
}
