package com.feragusper.buenosairesantesydespues.datasource;

import android.content.Context;

import com.feragusper.buenosairesantesydespues.cache.HistoricalRecordCache;
import com.feragusper.buenosairesantesydespues.entity.mapper.HistoricalRecordEntityJsonMapper;
import com.feragusper.buenosairesantesydespues.net.RestApi;
import com.feragusper.buenosairesantesydespues.net.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Factory that creates different implementations of {@link HistoricalRecordDataStore}.
 */
@Singleton
public class HistoricalRecordDataStoreFactory {

    private final Context context;
    private final HistoricalRecordCache historicalRecordCache;

    @Inject
    public HistoricalRecordDataStoreFactory(Context context, HistoricalRecordCache historicalRecordCache) {
        if (context == null || historicalRecordCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.historicalRecordCache = historicalRecordCache;
    }

    /**
     * Create {@link HistoricalRecordDataStore} from a user id.
     */
    public HistoricalRecordDataStore create(int historicalRecordId) {
        HistoricalRecordDataStore historicalRecordDataStore;

        if (!this.historicalRecordCache.isExpired() && this.historicalRecordCache.isCached(historicalRecordId)) {
            historicalRecordDataStore = new DiskHistoricalRecordDataStore(this.historicalRecordCache);
        } else {
            historicalRecordDataStore = createCloudDataStore();
        }

        return historicalRecordDataStore;
    }

    /**
     * Create {@link HistoricalRecordDataStore} to retrieve data from the Cloud.
     */
    public HistoricalRecordDataStore createCloudDataStore() {
        HistoricalRecordEntityJsonMapper historicalRecordEntityJsonMapper = new HistoricalRecordEntityJsonMapper();
        RestApi restApi = new RestApiImpl(this.context, historicalRecordEntityJsonMapper);

        return new CloudHistoricalRecordDataStore(restApi, this.historicalRecordCache);
    }
}
