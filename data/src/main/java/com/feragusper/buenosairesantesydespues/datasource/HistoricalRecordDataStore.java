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
package com.feragusper.buenosairesantesydespues.datasource;

import com.feragusper.buenosairesantesydespues.HistoricalRecordEntity;

import java.util.List;

import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface HistoricalRecordDataStore {
  /**
   * Get an {@link rx.Observable} which will emit a List of {@link HistoricalRecordEntity}.
   */
  Observable<List<HistoricalRecordEntity>> getHistoricalRecordEntityList();

  /**
   * Get an {@link rx.Observable} which will emit a {@link HistoricalRecordEntity} by its id.
   *
   * @param historicalRecordId The id to retrieve user data.
   */
  Observable<HistoricalRecordEntity> getHistoricalRecordEntityDetails(final int historicalRecordId);
}
