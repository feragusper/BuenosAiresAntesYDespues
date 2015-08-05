package com.feragusper.buenosairesantesydespues.di.modules;

import android.content.Context;

import com.feragusper.buenosairesantesydespues.AndroidApplication;
import com.feragusper.buenosairesantesydespues.UIThread;
import com.feragusper.buenosairesantesydespues.cache.HistoricalRecordCache;
import com.feragusper.buenosairesantesydespues.cache.HistoricalRecordCacheImpl;
import com.feragusper.buenosairesantesydespues.data.repository.HistoricalRecordDataRepository;
import com.feragusper.buenosairesantesydespues.domain.executor.PostExecutionThread;
import com.feragusper.buenosairesantesydespues.domain.executor.ThreadExecutor;
import com.feragusper.buenosairesantesydespues.domain.repository.HistoricalRecordRepository;
import com.feragusper.buenosairesantesydespues.executor.JobExecutor;
import com.feragusper.buenosairesantesydespues.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    HistoricalRecordCache provideHistoricalRecordCache(HistoricalRecordCacheImpl historicalRecordCache) {
        return historicalRecordCache;
    }

    @Provides
    @Singleton
    HistoricalRecordRepository provideHistoricalRecordRepository(HistoricalRecordDataRepository historicalRecordDataRepository) {
        return historicalRecordDataRepository;
    }
}
