package com.feragusper.buenosairesantesydespues.di.components;

import com.feragusper.buenosairesantesydespues.datasource.CloudHistoricalRecordDataStoreTest;
import com.feragusper.buenosairesantesydespues.datasource.DiskHistoricalRecordStoreTest;
import com.feragusper.buenosairesantesydespues.datasource.HistoricalRecordDataStoreFactoryTest;
import com.feragusper.buenosairesantesydespues.di.modules.DataStoreModule;

import dagger.Component;

/**
 * @author Fernando.Perez
 * @since 1.4
 */
@SuppressWarnings("WeakerAccess")
@Component(modules = DataStoreModule.class)
public interface DataStoreComponent {

    void inject(CloudHistoricalRecordDataStoreTest cloudHistoricalRecordDataStoreTest);

    void inject(DiskHistoricalRecordStoreTest diskHistoricalRecordStoreTest);

    void inject(HistoricalRecordDataStoreFactoryTest historicalRecordDataStoreFactoryTest);
}