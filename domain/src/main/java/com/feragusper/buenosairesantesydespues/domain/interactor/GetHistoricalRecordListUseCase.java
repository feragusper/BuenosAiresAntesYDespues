/**
 * Copyright (C) 2015 Fernando Perez Open Source Project
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
package com.feragusper.buenosairesantesydespues.domain.interactor;

import com.feragusper.buenosairesantesydespues.domain.executor.PostExecutionThread;
import com.feragusper.buenosairesantesydespues.domain.executor.ThreadExecutor;
import com.feragusper.buenosairesantesydespues.domain.repository.HistoricalRecordRepository;

import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link com.feragusper.buenosairesantesydespues.domain.HistoricalRecord}.
 */
public class GetHistoricalRecordListUseCase extends UseCase {

  private final HistoricalRecordRepository historicalRecordRepository;

  @Inject
  public GetHistoricalRecordListUseCase(HistoricalRecordRepository historicalRecordRepository, ThreadExecutor threadExecutor,
                                        PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.historicalRecordRepository = historicalRecordRepository;
  }

  @Override public Observable buildUseCaseObservable() {
    return this.historicalRecordRepository.getHistoricalRecords();
  }
}
