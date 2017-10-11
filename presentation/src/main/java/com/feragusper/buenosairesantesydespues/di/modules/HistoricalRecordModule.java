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
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Dagger module that provides historicalRecord related collaborators.
 */
@Module
public class HistoricalRecordModule {

    private String historicalRecordId = "";

    public HistoricalRecordModule() {
    }

    public HistoricalRecordModule(String historicalRecordId) {
        this.historicalRecordId = historicalRecordId;
    }

    @Provides
    @PerActivity
    @Named("historicalRecordList")
    GetHistoricalRecordListUseCase provideGetHistoricalRecordListUseCase(GetHistoricalRecordListUseCase getHistoricalRecordListUseCase) {
        return getHistoricalRecordListUseCase;
    }

    @Provides
    @PerActivity
    @Named("historicalRecordDetails")
    UseCase provideGetHistoricalRecordDetailsUseCase(HistoricalRecordRepository historicalRecordRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetHistoricalRecordDetailsUseCase(historicalRecordId, historicalRecordRepository, threadExecutor, postExecutionThread);
    }
}