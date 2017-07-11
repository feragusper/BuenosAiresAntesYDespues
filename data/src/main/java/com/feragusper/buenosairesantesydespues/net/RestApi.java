package com.feragusper.buenosairesantesydespues.net;

import com.feragusper.buenosairesantesydespues.entity.HistoricalRecordEntity;

import java.util.List;

import rx.Observable;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
    String API_URL_GET_HISTORICAL_RECORD_BASE_OLD = "https://spreadsheets.google.com/feeds/list/1YmRKJQy9N-Nl9BE1U9K7QYa6JCmtDTgFMyVGqSCZQc0/od6/public/values";
    String API_URL_GET_HISTORICAL_RECORD_BASE = "http://bsasantesydespues.com.ar/admin/api";
    String API_URL_GET_HISTORICAL_RECORD_ACTION_GET_POSTS = "/get_posts/";
    String API_URL_GET_HISTORICAL_RECORD_ACTION_GET_POST = "/get_post/";
    String API_URL_GET_HISTORICAL_RECORD_PARAM_COUNT = "/?count=1000";
    String API_URL_GET_HISTORICAL_RECORD_PARAM_POST_ID = "?post_id=";
    String API_PARAM_GET_HISTORICAL_RECORD_BASE_JSON = "?alt=json";
    String API_URL_GET_HISTORICAL_RECORD_LIST = API_URL_GET_HISTORICAL_RECORD_BASE + API_URL_GET_HISTORICAL_RECORD_ACTION_GET_POSTS + API_URL_GET_HISTORICAL_RECORD_PARAM_COUNT;
    String API_URL_GET_HISTORICAL_RECORD_BY_ID = API_URL_GET_HISTORICAL_RECORD_BASE + API_URL_GET_HISTORICAL_RECORD_ACTION_GET_POST + API_URL_GET_HISTORICAL_RECORD_PARAM_POST_ID;

    /**
     * Retrieves an {@link rx.Observable} which will emit a List of {@link SpreadsheetHistoricalRecordEntity}.
     */
    Observable<List<HistoricalRecordEntity>> getHistoricalRecordEntityList();

    Observable<HistoricalRecordEntity> getHistoricalRecordEntityById(String historicalRecordId);
}
