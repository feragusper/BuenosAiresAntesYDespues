package com.feragusper.buenosairesantesydespues.di.modules;

import com.feragusper.buenosairesantesydespues.net.RestApi;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * @author Fernando.Perez
 * @since 0.6
 */
@Module
public class DataStoreModule {

    @Provides
    RestApi provideRestApi() {
        return mock(RestApi.class);
    }

}
