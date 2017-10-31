package com.feragusper.buenosairesantesydespues.data.repository;

import com.feragusper.buenosairesantesydespues.datasource.HistoricalRecordDataStore;
import com.feragusper.buenosairesantesydespues.datasource.HistoricalRecordDataStoreFactory;
import com.feragusper.buenosairesantesydespues.domain.model.HistoricalRecord;
import com.feragusper.buenosairesantesydespues.domain.repository.HistoricalRecordRepository;
import com.feragusper.buenosairesantesydespues.entity.HistoricalRecordEntity;
import com.feragusper.buenosairesantesydespues.entity.HistoricalRecordListPageEntity;
import com.feragusper.buenosairesantesydespues.entity.mapper.HistoricalRecordEntityDataMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HistoricalRecordDataRepositoryTest {

    private static final String FAKE_HISTORICAL_RECORD_ID = "123";

    private HistoricalRecordRepository historicalRecordDataRepository;

    @Mock
    private HistoricalRecordDataStoreFactory mockHistoricalRecordDataStoreFactory;
    @Mock
    private HistoricalRecordEntityDataMapper mockHistoricalRecordEntityDataMapper;
    @Mock
    private HistoricalRecordDataStore mockHistoricalRecordDataStore;
    @Mock
    private HistoricalRecordEntity mockHistoricalRecordEntity;
    @Mock
    private HistoricalRecord mockHistoricalRecord;

    @Before
    public void setUp() {
        historicalRecordDataRepository = new HistoricalRecordDataRepository(mockHistoricalRecordDataStoreFactory, mockHistoricalRecordEntityDataMapper);
        given(mockHistoricalRecordDataStoreFactory.create(anyString())).willReturn(mockHistoricalRecordDataStore);
        given(mockHistoricalRecordDataStoreFactory.createCloudDataStore()).willReturn(mockHistoricalRecordDataStore);
    }

    @Test
    public void testGetHistoricalRecordsHappyCase() {
        HistoricalRecordListPageEntity historicalRecordListPageEntity = new HistoricalRecordListPageEntity();

        given(mockHistoricalRecordDataStore.getHistoricalRecordEntityList(anyInt(), anyInt())).willReturn(Observable.just(historicalRecordListPageEntity));

        historicalRecordDataRepository.getHistoricalRecords(20, 20);

        verify(mockHistoricalRecordDataStoreFactory).createCloudDataStore();
        verify(mockHistoricalRecordDataStore).getHistoricalRecordEntityList(20, 20);
    }

    @Test
    public void testGetHistoricalRecordHappyCase() {
        HistoricalRecordEntity historicalRecordEntity = new HistoricalRecordEntity();
        given(mockHistoricalRecordDataStore.getHistoricalRecordEntityDetails(FAKE_HISTORICAL_RECORD_ID)).willReturn(Observable.just(historicalRecordEntity));
        historicalRecordDataRepository.getHistoricalRecord(FAKE_HISTORICAL_RECORD_ID);

        verify(mockHistoricalRecordDataStoreFactory).create(FAKE_HISTORICAL_RECORD_ID);
        verify(mockHistoricalRecordDataStore).getHistoricalRecordEntityDetails(FAKE_HISTORICAL_RECORD_ID);
    }
}
