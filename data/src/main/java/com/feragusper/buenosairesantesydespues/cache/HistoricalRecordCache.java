package com.feragusper.buenosairesantesydespues.cache;

import com.feragusper.buenosairesantesydespues.HistoricalRecordEntity;

import rx.Observable;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * An interface representing a user Cache.
 */
public interface HistoricalRecordCache {
    /**
     * Gets an {@link rx.Observable} which will emit a {@link HistoricalRecordEntity}.
     *
     * @param userId The user id to retrieve data.
     */
    Observable<HistoricalRecordEntity> get(final String userId);

    /**
     * Puts and element into the cache.
     *
     * @param historicalRecordEntity Element to insert in the cache.
     */
    void put(HistoricalRecordEntity historicalRecordEntity);

    /**
     * Checks if an element (HistoricalRecord) exists in the cache.
     *
     * @param historicalRecordId The id used to look for inside the cache.
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(final String historicalRecordId);

    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();

    /**
     * Evict all elements of the cache.
     */
    void evictAll();
}
