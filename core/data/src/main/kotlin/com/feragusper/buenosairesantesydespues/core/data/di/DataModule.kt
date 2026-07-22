package com.feragusper.buenosairesantesydespues.core.data.di

import com.feragusper.buenosairesantesydespues.core.data.DefaultHistoricalRecordRepository
import com.feragusper.buenosairesantesydespues.core.data.HistoricalRecordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindsHistoricalRecordRepository(
        impl: DefaultHistoricalRecordRepository,
    ): HistoricalRecordRepository
}
