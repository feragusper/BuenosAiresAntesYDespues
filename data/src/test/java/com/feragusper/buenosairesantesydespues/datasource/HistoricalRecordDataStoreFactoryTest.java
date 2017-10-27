package com.feragusper.buenosairesantesydespues.datasource;

import com.feragusper.buenosairesantesydespues.ApplicationTestCase;
import com.feragusper.buenosairesantesydespues.cache.HistoricalRecordCache;
import com.feragusper.buenosairesantesydespues.di.components.DaggerDataStoreComponent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.robolectric.RuntimeEnvironment;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Test Cases for {@link HistoricalRecordDataStoreFactory}
 *
 * @author Fernando.Perez
 * @since 1.4
 */
public class HistoricalRecordDataStoreFactoryTest extends ApplicationTestCase {

    private static final String FAKE_HISTORICAL_RECORD_ID = "fake.historical.record.id";

    private HistoricalRecordDataStoreFactory historicalRecordDataStore;

    @Inject
    HistoricalRecordCache mockHistoricalRecordCache;

    @Before
    public void setUp() {
        DaggerDataStoreComponent.builder().build().inject(this);
        historicalRecordDataStore = new HistoricalRecordDataStoreFactory(RuntimeEnvironment.application, mockHistoricalRecordCache);
    }

    @Test
    public void testCreateDiskDataStore() {
        given(mockHistoricalRecordCache.isCached(FAKE_HISTORICAL_RECORD_ID)).willReturn(true);
        given(mockHistoricalRecordCache.isExpired()).willReturn(false);

        HistoricalRecordDataStore historicalRecordDataStore = this.historicalRecordDataStore.create(FAKE_HISTORICAL_RECORD_ID);

        assertThat(historicalRecordDataStore, is(notNullValue()));
        assertThat(historicalRecordDataStore, is(instanceOf(DiskHistoricalRecordDataStore.class)));

        verify(mockHistoricalRecordCache).isCached(FAKE_HISTORICAL_RECORD_ID);
        verify(mockHistoricalRecordCache).isExpired();
    }

    @Test
    public void testCreateCloudDataStore() {
        given(mockHistoricalRecordCache.isExpired()).willReturn(true);
        given(mockHistoricalRecordCache.isCached(FAKE_HISTORICAL_RECORD_ID)).willReturn(false);

        HistoricalRecordDataStore userDataStore = historicalRecordDataStore.create(FAKE_HISTORICAL_RECORD_ID);

        assertThat(userDataStore, is(notNullValue()));
        assertThat(userDataStore, is(instanceOf(CloudHistoricalRecordDataStore.class)));

        verify(mockHistoricalRecordCache).isExpired();
    }
}
