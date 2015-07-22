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
package com.feragusper.buenosairesantesydespues.entity.mapper;

import com.feragusper.buenosairesantesydespues.HistoricalRecordEntity;
import com.feragusper.buenosairesantesydespues.domain.HistoricalRecord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link HistoricalRecordEntity} (in the data layer) to {@link HistoricalRecord} in the
 * domain layer.
 */
@Singleton
public class HistoricalRecordEntityDataMapper {

  @Inject
  public HistoricalRecordEntityDataMapper() {}

  /**
   * Transform a {@link HistoricalRecordEntity} into an {@link HistoricalRecord}.
   *
   * @param historicalRecordEntity Object to be transformed.
   * @return {@link HistoricalRecord} if valid {@link HistoricalRecordEntity} otherwise null.
   */
  public HistoricalRecord transform(HistoricalRecordEntity historicalRecordEntity) {
    HistoricalRecord historicalRecord = null;
    if (historicalRecordEntity != null) {
      historicalRecord = new HistoricalRecord(historicalRecordEntity.getHistoricalRecordId());
      historicalRecord.setCoverUrl(historicalRecordEntity.getCoverUrl());
      historicalRecord.setFullName(historicalRecordEntity.getFullname());
      historicalRecord.setDescription(historicalRecordEntity.getDescription());
      historicalRecord.setFollowers(historicalRecordEntity.getFollowers());
      historicalRecord.setEmail(historicalRecordEntity.getEmail());
    }

    return historicalRecord;
  }

  /**
   * Transform a List of {@link HistoricalRecordEntity} into a Collection of {@link HistoricalRecord}.
   *
   * @param historicalRecordEntityCollection Object Collection to be transformed.
   * @return {@link HistoricalRecord} if valid {@link HistoricalRecordEntity} otherwise null.
   */
  public List<HistoricalRecord> transform(Collection<HistoricalRecordEntity> historicalRecordEntityCollection) {
    List<HistoricalRecord> historicalRecordList = new ArrayList<>(20);
    HistoricalRecord historicalRecord;
    for (HistoricalRecordEntity historicalRecordEntity : historicalRecordEntityCollection) {
      historicalRecord = transform(historicalRecordEntity);
      if (historicalRecord != null) {
        historicalRecordList.add(historicalRecord);
      }
    }

    return historicalRecordList;
  }
}
