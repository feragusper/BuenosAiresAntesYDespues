package com.feragusper.buenosairesantesydespues.domain.repository;

import com.feragusper.buenosairesantesydespues.domain.HistoricalRecord;

import java.util.List;

import rx.Observable;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Interface that represents a Repository for getting {@link HistoricalRecord} related data.
 */
public interface HistoricalRecordRepository {
    /**
     * Get an {@link Observable} which will emit a List of {@link HistoricalRecord}.
     *
     * @param offset
     * @param limit
     */
    Observable<List<HistoricalRecord>> getHistoricalRecords(int offset, int limit);

    /**
     * Get an {@link Observable} which will emit a {@link HistoricalRecord}.
     *
     * @param historicalRecordId The historical record id used to retrieve user data.
     */
    Observable<HistoricalRecord> getHistoricalRecord(final String historicalRecordId);
}
