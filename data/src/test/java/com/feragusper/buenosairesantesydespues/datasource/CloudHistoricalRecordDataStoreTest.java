package com.feragusper.buenosairesantesydespues.datasource;

import com.feragusper.buenosairesantesydespues.cache.HistoricalRecordCache;
import com.feragusper.buenosairesantesydespues.di.components.DaggerDataStoreComponent;
import com.feragusper.buenosairesantesydespues.net.RestApi;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test Cases for {@link CloudHistoricalRecordDataStore}
 *
 * @author Fernando.Perez
 * @since 1.4
 */
public class CloudHistoricalRecordDataStoreTest {

    //region Properties
    private CloudHistoricalRecordDataStore cloudDataStore;

    @Inject
    RestApi mockRestApi;

    @Inject
    HistoricalRecordCache historicalRecordCache;
    //endregion

    //region Public Implementation
    @Before
    public void setUp() {
        DaggerDataStoreComponent.builder().build().inject(this);
        cloudDataStore = new CloudHistoricalRecordDataStore(mockRestApi, historicalRecordCache);
    }

    @Test
    public void testGetHistoricalRecordEntityList() {
        cloudDataStore.getHistoricalRecordEntityList(anyInt(), anyInt());
        verify(mockRestApi, times(1)).getHistoricalRecordEntityList(anyInt(), anyInt());
    }

    @Test
    public void testGetHistoricalRecordEntityDetails() {
        cloudDataStore.getHistoricalRecordEntityDetails(anyString());
        verify(mockRestApi, times(1)).getHistoricalRecordEntityById(anyString());
    }
    //endregion

}
