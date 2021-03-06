package com.feragusper.buenosairesantesydespues.data.repository;

import com.feragusper.buenosairesantesydespues.datasource.HistoricalRecordDataStore;
import com.feragusper.buenosairesantesydespues.datasource.HistoricalRecordDataStoreFactory;
import com.feragusper.buenosairesantesydespues.domain.model.HistoricalRecord;
import com.feragusper.buenosairesantesydespues.domain.model.HistoricalRecordListPage;
import com.feragusper.buenosairesantesydespues.domain.repository.HistoricalRecordRepository;
import com.feragusper.buenosairesantesydespues.entity.HistoricalRecordListPageEntity;
import com.feragusper.buenosairesantesydespues.entity.mapper.HistoricalRecordEntityDataMapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * {@link HistoricalRecordRepository} for retrieving HistoricalRecord data.
 */
@Singleton
public class HistoricalRecordDataRepository implements HistoricalRecordRepository {

    private final HistoricalRecordDataStoreFactory historicalRecordDataStoreFactory;
    private final HistoricalRecordEntityDataMapper historicalRecordEntityDataMapper;

    /**
     * Constructs a {@link HistoricalRecordRepository}.
     *
     * @param dataStoreFactory                 A factory to construct different data source implementations.
     * @param historicalRecordEntityDataMapper {@link HistoricalRecordEntityDataMapper}.
     */
    @SuppressWarnings("WeakerAccess")
    @Inject
    public HistoricalRecordDataRepository(HistoricalRecordDataStoreFactory dataStoreFactory,
                                          HistoricalRecordEntityDataMapper historicalRecordEntityDataMapper) {
        this.historicalRecordDataStoreFactory = dataStoreFactory;
        this.historicalRecordEntityDataMapper = historicalRecordEntityDataMapper;
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<HistoricalRecordListPage> getHistoricalRecords(int page, int count) {
        final HistoricalRecordDataStore historicalRecordDataStore = this.historicalRecordDataStoreFactory.createCloudDataStore();
        return historicalRecordDataStore.getHistoricalRecordEntityList(page, count).map(historicalRecordEntities -> this.historicalRecordEntityDataMapper.transform(historicalRecordEntities));
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<HistoricalRecord> getHistoricalRecord(String historicalRecordId) {
        final HistoricalRecordDataStore historicalRecordDataStore = this.historicalRecordDataStoreFactory.create(historicalRecordId);
        return historicalRecordDataStore.getHistoricalRecordEntityDetails(historicalRecordId).map(historicalRecordEntity -> this.historicalRecordEntityDataMapper.transform(historicalRecordEntity));
    }
}
