package com.feragusper.buenosairesantesydespues.datasource;

import com.feragusper.buenosairesantesydespues.cache.HistoricalRecordCache;
import com.feragusper.buenosairesantesydespues.entity.HistoricalRecordEntity;
import com.feragusper.buenosairesantesydespues.net.RestApi;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * {@link HistoricalRecordDataStore} implementation based on connections to the api (Cloud).
 */
class CloudHistoricalRecordDataStore implements HistoricalRecordDataStore {

    private final RestApi restApi;
    private final HistoricalRecordCache historicalRecordCache;

    private final Action1<List<HistoricalRecordEntity>> saveToCacheAction =
            historicalRecordList -> {
                if (historicalRecordList != null) {
                    for (HistoricalRecordEntity historicalRecordEntity : historicalRecordList) {
                        CloudHistoricalRecordDataStore.this.historicalRecordCache.put(historicalRecordEntity);
                    }
                }
            };

    /**
     * Construct a {@link HistoricalRecordDataStore} based on connections to the api (Cloud).
     *
     * @param restApi               The {@link RestApi} implementation to use.
     * @param historicalRecordCache A {@link HistoricalRecordCache} to cache data retrieved from the api.
     */
    CloudHistoricalRecordDataStore(RestApi restApi, HistoricalRecordCache historicalRecordCache) {
        this.restApi = restApi;
        this.historicalRecordCache = historicalRecordCache;
    }

    @Override
    public Observable<List<HistoricalRecordEntity>> getHistoricalRecordEntityList(int page, int count) {
        return this.restApi.getHistoricalRecordEntityList(page, count).doOnNext(saveToCacheAction);
    }

    @Override
    public Observable<HistoricalRecordEntity> getHistoricalRecordEntityDetails(String historicalRecordId) {
        return this.restApi.getHistoricalRecordEntityById(historicalRecordId);
    }

}
