package com.feragusper.buenosairesantesydespues.datasource;

import com.feragusper.buenosairesantesydespues.cache.HistoricalRecordCache;
import com.feragusper.buenosairesantesydespues.di.components.DaggerDataStoreComponent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Test Cases for {@link DiskHistoricalRecordDataStore}
 *
 * @author Fernando.Perez
 * @since 1.4
 */
public class DiskHistoricalRecordStoreTest {

    private static final String FAKE_HISTORICAL_RECORD_ID = "fake.historical.record.id";

    private DiskHistoricalRecordDataStore diskHistoricalRecordDataStore;

    @Inject
    HistoricalRecordCache mockHistoricalRecordCache;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        DaggerDataStoreComponent.builder().build().inject(this);
        diskHistoricalRecordDataStore = new DiskHistoricalRecordDataStore(mockHistoricalRecordCache);
    }

    @Test
    public void testGetHistoricalRecordEntityListUnsupported() {
        expectedException.expect(UnsupportedOperationException.class);
        diskHistoricalRecordDataStore.getHistoricalRecordEntityList(1, 1);
    }

    @Test
    public void testGetHistoricalRecordEntityDetailsFromCache() {
        diskHistoricalRecordDataStore.getHistoricalRecordEntityDetails(FAKE_HISTORICAL_RECORD_ID);
        verify(mockHistoricalRecordCache).get(FAKE_HISTORICAL_RECORD_ID);
    }
}
