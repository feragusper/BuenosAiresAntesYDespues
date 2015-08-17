package com.feragusper.buenosairesantesydespues.net;

import com.feragusper.buenosairesantesydespues.HistoricalRecordEntity;

import java.util.List;

import rx.Observable;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
    static final String API_URL_GET_HISTORICAL_RECORD_BASE = "https://spreadsheets.google.com/feeds/list/1YmRKJQy9N-Nl9BE1U9K7QYa6JCmtDTgFMyVGqSCZQc0/od6/public/values";
    static final String API_PARAM_GET_HISTORICAL_RECORD_BASE_JSON = "?alt=json";
    static final String API_URL_GET_HISTORICAL_RECORD_LIST = API_URL_GET_HISTORICAL_RECORD_BASE + API_PARAM_GET_HISTORICAL_RECORD_BASE_JSON;

    /**
     * Retrieves an {@link rx.Observable} which will emit a List of {@link HistoricalRecordEntity}.
     */
    Observable<List<HistoricalRecordEntity>> getHistoricalRecordEntityList();

    Observable<HistoricalRecordEntity> getHistoricalRecordEntityById(String historicalRecordId);
}
