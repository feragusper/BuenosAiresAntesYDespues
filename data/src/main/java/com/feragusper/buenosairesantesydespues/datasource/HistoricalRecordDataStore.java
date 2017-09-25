package com.feragusper.buenosairesantesydespues.datasource;

import com.feragusper.buenosairesantesydespues.entity.HistoricalRecordEntity;

import java.util.List;

import rx.Observable;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Interface that represents a data store from where data is retrieved.
 */
public interface HistoricalRecordDataStore {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link HistoricalRecordEntity}.
     * @param offset
     * @param limit
     */
    Observable<List<HistoricalRecordEntity>> getHistoricalRecordEntityList(int offset, int limit);

    /**
     * Get an {@link rx.Observable} which will emit a {@link HistoricalRecordEntity} by its id.
     *
     * @param historicalRecordId The id to retrieve user data.
     */
    Observable<HistoricalRecordEntity> getHistoricalRecordEntityDetails(final String historicalRecordId);
}
