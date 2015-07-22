/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feragusper.buenosairesantesydespues.cache;

import com.feragusper.buenosairesantesydespues.HistoricalRecordEntity;

import rx.Observable;

/**
 * An interface representing a user Cache.
 */
public interface HistoricalRecordCache {
  /**
   * Gets an {@link rx.Observable} which will emit a {@link HistoricalRecordEntity}.
   *
   * @param userId The user id to retrieve data.
   */
  Observable<HistoricalRecordEntity> get(final int userId);

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
  boolean isCached(final int historicalRecordId);

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
