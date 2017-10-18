package com.feragusper.buenosairesantesydespues.di.modules;

import com.feragusper.buenosairesantesydespues.cache.HistoricalRecordCache;
import com.feragusper.buenosairesantesydespues.net.RestApi;

import dagger.Module;
import dagger.Provides;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Fernando.Perez
 * @since 1.4
 */
@Module
public class DataStoreModule {

    @Provides
    RestApi provideRestApi() {
        final RestApi mockRestApi = mock(RestApi.class);
        when(mockRestApi.getHistoricalRecordEntityList(anyInt(), anyInt())).thenReturn(mock(Observable.class));
        when(mockRestApi.getHistoricalRecordEntityById(anyString())).thenReturn(mock(Observable.class));
        return mockRestApi;
    }

    @Provides
    HistoricalRecordCache provideHistoricalRecordCache() {
        return mock(HistoricalRecordCache.class);
    }
}
