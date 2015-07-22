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
package com.feragusper.buenosairesantesydespues.mapper;

import com.feragusper.buenosairesantesydespues.di.PerActivity;
import com.feragusper.buenosairesantesydespues.domain.HistoricalRecord;
import com.feragusper.buenosairesantesydespues.model.HistoricalRecordModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link com.feragusper.buenosairesantesydespues.domain.HistoricalRecord} (in the domain layer) to {@link HistoricalRecordModel} in the
 * presentation layer.
 */
@PerActivity
public class HistoricalRecordModelDataMapper {

  @Inject
  public HistoricalRecordModelDataMapper() {}

  /**
   * Transform a {@link HistoricalRecord} into an {@link HistoricalRecordModel}.
   *
   * @param historicalRecord Object to be transformed.
   * @return {@link HistoricalRecordModel}.
   */
  public HistoricalRecordModel transform(HistoricalRecord historicalRecord) {
    if (historicalRecord == null) {
      throw new IllegalArgumentException("Cannot transform a null value");
    }
    HistoricalRecordModel historicalRecordModel = new HistoricalRecordModel(historicalRecord.getHistoricalRecordId());
    historicalRecordModel.setCoverUrl(historicalRecord.getCoverUrl());
    historicalRecordModel.setFullName(historicalRecord.getFullName());
    historicalRecordModel.setEmail(historicalRecord.getEmail());
    historicalRecordModel.setDescription(historicalRecord.getDescription());
    historicalRecordModel.setFollowers(historicalRecord.getFollowers());

    return historicalRecordModel;
  }

  /**
   * Transform a Collection of {@link User} into a Collection of {@link HistoricalRecordModel}.
   *
   * @param historicalRecordsCollection Objects to be transformed.
   * @return List of {@link HistoricalRecordModel}.
   */
  public Collection<HistoricalRecordModel> transform(Collection<HistoricalRecord> historicalRecordsCollection) {
    Collection<HistoricalRecordModel> historicalRecordModelsCollection;

    if (historicalRecordsCollection != null && !historicalRecordsCollection.isEmpty()) {
      historicalRecordModelsCollection = new ArrayList<>();
      for (HistoricalRecord historicalRecord : historicalRecordsCollection) {
        historicalRecordModelsCollection.add(transform(historicalRecord));
      }
    } else {
      historicalRecordModelsCollection = Collections.emptyList();
    }

    return historicalRecordModelsCollection;
  }
}
