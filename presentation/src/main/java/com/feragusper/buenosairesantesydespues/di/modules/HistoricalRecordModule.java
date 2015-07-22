/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feragusper.buenosairesantesydespues.di.modules;

import com.feragusper.buenosairesantesydespues.di.PerActivity;
import com.feragusper.buenosairesantesydespues.domain.executor.PostExecutionThread;
import com.feragusper.buenosairesantesydespues.domain.executor.ThreadExecutor;
import com.feragusper.buenosairesantesydespues.domain.interactor.GetHistoricalRecordDetailsUseCase;
import com.feragusper.buenosairesantesydespues.domain.interactor.GetHistoricalRecordListUseCase;
import com.feragusper.buenosairesantesydespues.domain.interactor.UseCase;
import com.feragusper.buenosairesantesydespues.domain.repository.HistoricalRecordRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides historicalRecord related collaborators.
 */
@Module
public class HistoricalRecordModule {

    private int historicalRecordId = -1;

    public HistoricalRecordModule() {
    }

    public HistoricalRecordModule(int historicalRecordId) {
        this.historicalRecordId = historicalRecordId;
    }

    @Provides
    @PerActivity
    @Named("historicalRecordList")
    UseCase provideGetHistoricalRecordListUseCase(
            GetHistoricalRecordListUseCase getHistoricalRecordListUseCase) {
        return getHistoricalRecordListUseCase;
    }

    @Provides
    @PerActivity
    @Named("historicalRecordDetails")
    UseCase provideGetHistoricalRecordDetailsUseCase(
            HistoricalRecordRepository historicalRecordRepository, ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        return new GetHistoricalRecordDetailsUseCase(historicalRecordId, historicalRecordRepository, threadExecutor, postExecutionThread);
    }
}