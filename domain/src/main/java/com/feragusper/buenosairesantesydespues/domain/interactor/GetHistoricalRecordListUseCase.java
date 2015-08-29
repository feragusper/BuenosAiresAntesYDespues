package com.feragusper.buenosairesantesydespues.domain.interactor;

import com.feragusper.buenosairesantesydespues.domain.executor.PostExecutionThread;
import com.feragusper.buenosairesantesydespues.domain.executor.ThreadExecutor;
import com.feragusper.buenosairesantesydespues.domain.repository.HistoricalRecordRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
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

    @Override
    public Observable buildUseCaseObservable() {
        return this.historicalRecordRepository.getHistoricalRecords();
    }
}
