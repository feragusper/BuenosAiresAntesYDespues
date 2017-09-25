package com.feragusper.buenosairesantesydespues.domain.interactor;

import com.feragusper.buenosairesantesydespues.domain.executor.PostExecutionThread;
import com.feragusper.buenosairesantesydespues.domain.executor.ThreadExecutor;
import com.feragusper.buenosairesantesydespues.domain.repository.HistoricalRecordRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Fernando.Perez
 * @since 0.1
 *
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link com.feragusper.buenosairesantesydespues.domain.HistoricalRecord}.
 */
public class GetHistoricalRecordDetailsUseCase extends UseCase {

    private final String historicalRecordId;
    private final HistoricalRecordRepository historicalRecordRepository;

    @Inject
    public GetHistoricalRecordDetailsUseCase(String historicalRecordId, HistoricalRecordRepository historicalRecordRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.historicalRecordId = historicalRecordId;
        this.historicalRecordRepository = historicalRecordRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.historicalRecordRepository.getHistoricalRecord(this.historicalRecordId);
    }

}