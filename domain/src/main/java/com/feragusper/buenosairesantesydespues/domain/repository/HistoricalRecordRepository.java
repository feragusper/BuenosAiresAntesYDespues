package com.feragusper.buenosairesantesydespues.domain.repository;

import com.feragusper.buenosairesantesydespues.domain.HistoricalRecord;

import java.util.List;

import rx.Observable;

/**
 * @author Fernando.Perez
 * @since 0.1
 *
 * Interface that represents a Repository for getting {@link HistoricalRecord} related data.
 */
public interface HistoricalRecordRepository {
    /**
     * Get an {@link Observable} which will emit a List of {@link HistoricalRecord}.
     */
    Observable<List<HistoricalRecord>> getHistoricalRecords();

    /**
     * Get an {@link Observable} which will emit a {@link HistoricalRecord}.
     *
     * @param userId The user id used to retrieve user data.
     */
    Observable<HistoricalRecord> getHistoricalRecord(final int userId);
}
