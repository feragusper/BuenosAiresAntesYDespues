package com.feragusper.buenosairesantesydespues.di.components;

import com.feragusper.buenosairesantesydespues.datasource.CloudDataStoreTest;
import com.feragusper.buenosairesantesydespues.di.modules.DataStoreModule;

import dagger.Component;

/**
 * @author Fernando.Perez
 * @since 0.6
 */
@Component(modules = DataStoreModule.class)
public interface DataStoreComponent {

    void inject(CloudDataStoreTest cloudDataStoreTest);

}