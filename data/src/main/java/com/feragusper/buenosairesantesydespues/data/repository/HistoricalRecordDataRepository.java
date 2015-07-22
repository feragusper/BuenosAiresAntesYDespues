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
package com.feragusper.buenosairesantesydespues.data.repository;

import com.feragusper.buenosairesantesydespues.datasource.HistoricalRecordDataStoreFactory;
import com.feragusper.buenosairesantesydespues.domain.HistoricalRecord;
import com.feragusper.buenosairesantesydespues.domain.repository.HistoricalRecordRepository;
import com.feragusper.buenosairesantesydespues.datasource.HistoricalRecordDataStore;
import com.feragusper.buenosairesantesydespues.entity.mapper.HistoricalRecordEntityDataMapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;

/**
 * {@link HistoricalRecordRepository} for retrieving user data.
 */
@Singleton
public class HistoricalRecordDataRepository implements HistoricalRecordRepository {

  private final HistoricalRecordDataStoreFactory historicalRecordDataStoreFactory;
  private final HistoricalRecordEntityDataMapper historicalRecordEntityDataMapper;

  /**
   * Constructs a {@link HistoricalRecordRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param historicalRecordEntityDataMapper {@link HistoricalRecordEntityDataMapper}.
   */
  @Inject
  public HistoricalRecordDataRepository(HistoricalRecordDataStoreFactory dataStoreFactory,
                                        HistoricalRecordEntityDataMapper historicalRecordEntityDataMapper) {
    this.historicalRecordDataStoreFactory = dataStoreFactory;
    this.historicalRecordEntityDataMapper = historicalRecordEntityDataMapper;
  }

  @SuppressWarnings("Convert2MethodRef")
  @Override public Observable<List<HistoricalRecord>> getHistoricalRecords() {
    //we always get all users from the cloud
    final HistoricalRecordDataStore historicalRecordDataStore = this.historicalRecordDataStoreFactory.createCloudDataStore();
    return historicalRecordDataStore.getHistoricalRecordEntityList()
        .map(historicalRecordEntities -> this.historicalRecordEntityDataMapper.transform(historicalRecordEntities));
  }

  @SuppressWarnings("Convert2MethodRef")
  @Override public Observable<HistoricalRecord> getHistoricalRecord(int historicalRecordId) {
    final HistoricalRecordDataStore historicalRecordDataStore = this.historicalRecordDataStoreFactory.create(historicalRecordId);
    return historicalRecordDataStore.getHistoricalRecordEntityDetails(historicalRecordId)
        .map(historicalRecordEntity -> this.historicalRecordEntityDataMapper.transform(historicalRecordEntity));
  }
}
