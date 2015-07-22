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

import android.content.Context;
import com.feragusper.buenosairesantesydespues.cache.HistoricalRecordCache;
import com.feragusper.buenosairesantesydespues.entity.mapper.HistoricalRecordEntityJsonMapper;
import com.feragusper.buenosairesantesydespues.net.RestApi;
import com.feragusper.buenosairesantesydespues.net.RestApiImpl;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link HistoricalRecordDataStore}.
 */
@Singleton
public class HistoricalRecordDataStoreFactory {

  private final Context context;
  private final HistoricalRecordCache historicalRecordCache;

  @Inject
  public HistoricalRecordDataStoreFactory(Context context, HistoricalRecordCache historicalRecordCache) {
    if (context == null || historicalRecordCache == null) {
      throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
    }
    this.context = context.getApplicationContext();
    this.historicalRecordCache = historicalRecordCache;
  }

  /**
   * Create {@link HistoricalRecordDataStore} from a user id.
   */
  public HistoricalRecordDataStore create(int historicalRecordId) {
    HistoricalRecordDataStore historicalRecordDataStore;

    if (!this.historicalRecordCache.isExpired() && this.historicalRecordCache.isCached(historicalRecordId)) {
      historicalRecordDataStore = new DiskHistoricalRecordDataStore(this.historicalRecordCache);
    } else {
      historicalRecordDataStore = createCloudDataStore();
    }

    return historicalRecordDataStore;
  }

  /**
   * Create {@link HistoricalRecordDataStore} to retrieve data from the Cloud.
   */
  public HistoricalRecordDataStore createCloudDataStore() {
    HistoricalRecordEntityJsonMapper historicalRecordEntityJsonMapper = new HistoricalRecordEntityJsonMapper();
    RestApi restApi = new RestApiImpl(this.context, historicalRecordEntityJsonMapper);

    return new CloudHistoricalRecordDataStore(restApi, this.historicalRecordCache);
  }
}
