package com.feragusper.buenosairesantesydespues.net;

import android.net.ConnectivityManager;
import android.content.Context;
import android.net.NetworkInfo;
import android.util.Log;

import com.feragusper.buenosairesantesydespues.ApiConnection;
import com.feragusper.buenosairesantesydespues.HistoricalRecordEntity;
import com.feragusper.buenosairesantesydespues.entity.mapper.HistoricalRecordEntityJsonMapper;
import com.feragusper.buenosairesantesydespues.exception.NetworkConnectionException;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p/>
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {

    private final Context context;
    private final HistoricalRecordEntityJsonMapper historicalRecordEntityJsonMapper;

    /**
     * Constructor of the class
     *
     * @param context                          {@link android.content.Context}.
     * @param historicalRecordEntityJsonMapper {@link HistoricalRecordEntityJsonMapper}.
     */
    public RestApiImpl(Context context, HistoricalRecordEntityJsonMapper historicalRecordEntityJsonMapper) {
        if (context == null || historicalRecordEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.historicalRecordEntityJsonMapper = historicalRecordEntityJsonMapper;
    }

    @Override
    public Observable<List<HistoricalRecordEntity>> getHistoricalRecordEntityList() {
        return Observable.create(new Observable.OnSubscribe<List<HistoricalRecordEntity>>() {
            @Override
            public void call(Subscriber<? super List<HistoricalRecordEntity>> subscriber) {

                if (isThereInternetConnection()) {
                    try {
                        String responseHistoricalRecordEntities = getHistoricalRecordEntitiesFromApi();
                        if (responseHistoricalRecordEntities != null) {
                            subscriber.onNext(historicalRecordEntityJsonMapper.transformUserEntityCollection(new JSONObject(responseHistoricalRecordEntities).getJSONObject("feed").getJSONArray("entry").toString()));
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(new NetworkConnectionException());
                        }
                    } catch (Exception e) {
                        Log.e(this.getClass().getName(), "An error ocurred while trying to get the results for the list of historical records", e);
                        subscriber.onError(new NetworkConnectionException(e.getCause()));
                    }
                } else {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });
    }

    @Override
    public Observable<HistoricalRecordEntity> getHistoricalRecordEntityById(String historicalRecordId) {
        return Observable.create(new Observable.OnSubscribe<HistoricalRecordEntity>() {
            @Override
            public void call(Subscriber<? super HistoricalRecordEntity> subscriber) {
                if (isThereInternetConnection()) {
                    try {
                        String responseHistoricalRecordEntity = getHistoricalRecordEntityFromApi(historicalRecordId);
                        if (responseHistoricalRecordEntity != null) {
                            subscriber.onNext(historicalRecordEntityJsonMapper.transformHistoricalRecordEntity(new JSONObject(responseHistoricalRecordEntity).getJSONObject("entry").toString()));
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(new NetworkConnectionException());
                        }
                    } catch (Exception e) {
                        Log.e(this.getClass().getName(), "An error ocurred while trying to get the results for the list of historical records", e);
                        subscriber.onError(new NetworkConnectionException(e.getCause()));
                    }
                } else {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });
    }

    private String getHistoricalRecordEntityFromApi(String historicalRecordId) throws MalformedURLException {
        return ApiConnection.createGET(API_URL_GET_HISTORICAL_RECORD_BASE + "/" + historicalRecordId + API_PARAM_GET_HISTORICAL_RECORD_BASE_JSON).requestSyncCall();
    }

    private String getHistoricalRecordEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGET(API_URL_GET_HISTORICAL_RECORD_LIST).requestSyncCall();
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
