package com.feragusper.buenosairesantesydespues.datasource;

import com.feragusper.buenosairesantesydespues.net.RestApi;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test Cases for {@link CloudHistoricalRecordDataStore}
 *
 * @author Fernando.Perez
 * @since 0.6
 */
public class CloudDataStoreTest {

    //region Properties
    private CloudHistoricalRecordDataStore cloudDataStore;

    @Inject
    private RestApi mockRestApi;
    //endregion

    //region Public Implementation
    @Before
    public void setUp() {
//        DaggerDataStoreComponent.builder().build().inject(this);
//        cloudDataStore = new CloudDataStore(mockRestApi);
    }

    @Test
    public void testGetAreaListFromApi() {
        cloudDataStore.getHistoricalRecordEntityList(1, 20);
        verify(mockRestApi, times(1)).getHistoricalRecordEntityList(1, 20);
    }
    //endregion

}
