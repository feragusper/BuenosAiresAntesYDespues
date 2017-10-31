package com.feragusper.buenosairesantesydespues.domain.interactor;

import com.feragusper.buenosairesantesydespues.domain.executor.PostExecutionThread;
import com.feragusper.buenosairesantesydespues.domain.executor.ThreadExecutor;
import com.feragusper.buenosairesantesydespues.domain.model.HistoricalRecord;
import com.feragusper.buenosairesantesydespues.domain.repository.HistoricalRecordRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link HistoricalRecord}.
 */
public class GetHistoricalRecordListUseCase extends UseCase {

    private static final int ITEMS_PER_PAGE = 20;
    private final HistoricalRecordRepository historicalRecordRepository;
    private int page;

    @Inject
    public GetHistoricalRecordListUseCase(HistoricalRecordRepository historicalRecordRepository, ThreadExecutor threadExecutor,
                                          PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.historicalRecordRepository = historicalRecordRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.historicalRecordRepository.getHistoricalRecords(page, ITEMS_PER_PAGE);
    }

    public void setPage(int page) {
        this.page = page;
    }
}
