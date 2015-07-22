/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feragusper.buenosairesantesydespues.datasource;

import com.feragusper.buenosairesantesydespues.HistoricalRecordEntity;
import com.feragusper.buenosairesantesydespues.cache.HistoricalRecordCache;
import com.feragusper.buenosairesantesydespues.net.RestApi;
import java.util.List;
import rx.Observable;
import rx.functions.Action1;

/**
 * {@link HistoricalRecordDataStore} implementation based on connections to the api (Cloud).
 */
public class CloudHistoricalRecordDataStore implements HistoricalRecordDataStore {

  private final RestApi restApi;
  private final HistoricalRecordCache historicalRecordCache;

  private final Action1<HistoricalRecordEntity> saveToCacheAction =
      historicalRecord -> {
        if (historicalRecord != null) {
          CloudHistoricalRecordDataStore.this.historicalRecordCache.put(historicalRecord);
        }
      };

  /**
   * Construct a {@link HistoricalRecordDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link RestApi} implementation to use.
   * @param historicalRecordCache A {@link HistoricalRecordCache} to cache data retrieved from the api.
   */
  public CloudHistoricalRecordDataStore(RestApi restApi, HistoricalRecordCache historicalRecordCache) {
    this.restApi = restApi;
    this.historicalRecordCache = historicalRecordCache;
  }

  @Override public Observable<List<HistoricalRecordEntity>> getHistoricalRecordEntityList() {
    return this.restApi.getHistoricalRecordEntityList();
  }

  @Override public Observable<HistoricalRecordEntity> getHistoricalRecordEntityDetails(final int historicalRecordId) {
    return this.restApi.getHistoricalRecordEntityById(historicalRecordId)
        .doOnNext(saveToCacheAction);
  }
}
