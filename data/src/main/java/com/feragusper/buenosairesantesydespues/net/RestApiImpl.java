package com.feragusper.buenosairesantesydespues.net;

import android.net.ConnectivityManager;
import android.content.Context;
import android.net.NetworkInfo;

import com.feragusper.buenosairesantesydespues.ApiConnection;
import com.feragusper.buenosairesantesydespues.HistoricalRecordEntity;
import com.feragusper.buenosairesantesydespues.entity.mapper.HistoricalRecordEntityJsonMapper;
import com.feragusper.buenosairesantesydespues.exception.NetworkConnectionException;

import java.net.MalformedURLException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * @author Fernando.Perez
 * @since 0.1
 *
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
                            subscriber.onNext(historicalRecordEntityJsonMapper.transformUserEntityCollection(
                                    responseHistoricalRecordEntities));
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(new NetworkConnectionException());
                        }
                    } catch (Exception e) {
                        subscriber.onError(new NetworkConnectionException(e.getCause()));
                    }
                } else {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });
    }

    @Override
    public Observable<HistoricalRecordEntity> getHistoricalRecordEntityById(final int historicalRecordId) {
        return Observable.create(new Observable.OnSubscribe<HistoricalRecordEntity>() {
            @Override
            public void call(Subscriber<? super HistoricalRecordEntity> subscriber) {

                if (isThereInternetConnection()) {
                    try {
                        String responseHistoricalRecordDetails = getHistoricalRecordDetailsFromApi(historicalRecordId);
                        if (responseHistoricalRecordDetails != null) {
//                            subscriber.onNext(historicalRecordEntityJsonMapper.transformHistoricalRecordEntity(responseHistoricalRecordDetails));
                            final HistoricalRecordEntity historicalRecordEntity = new HistoricalRecordEntity();
                            historicalRecordEntity.setTitle("Av Virrey V\u00e9rtiz - 1916");
                            historicalRecordEntity.setImageURLBefore("http://bsasantesydespues.com.ar/fotos/castillo_los_leones/antes.jpg");
                            historicalRecordEntity.setImageURLAfter("http://bsasantesydespues.com.ar/fotos/castillo_los_leones/ahora.jpg");
                            historicalRecordEntity.setAddress("Av Virrey V\u00e9rtiz y Jos\u00e9 Hern\u00e1ndez");
                            historicalRecordEntity.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam imperdiet elit nisl, sit amet tristique lectus egestas id. Ut magna lorem, auctor id lobortis rhoncus, convallis non tortor.");
                            historicalRecordEntity.setYear("1916");
                            historicalRecordEntity.setNeighborhood("Belgrano");
                            historicalRecordEntity.setLat(-34.561179);
                            historicalRecordEntity.setLng(-58.445531);
                            historicalRecordEntity.setShareURL("http://bsasantesydespues.com.ar/#42");
                            subscriber.onNext(historicalRecordEntity);
                            subscriber.onCompleted();
                        } else {
                            subscriber.onError(new NetworkConnectionException());
                        }
                    } catch (Exception e) {
                        subscriber.onError(new NetworkConnectionException(e.getCause()));
                    }
                } else {
                    subscriber.onError(new NetworkConnectionException());
                }
            }
        });
    }

    private String getHistoricalRecordEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGET(API_URL_GET_USER_LIST).requestSyncCall();
    }

    private String getHistoricalRecordDetailsFromApi(int historicalRecordId) throws MalformedURLException {
        String apiUrl = API_URL_GET_USER_DETAILS + historicalRecordId + ".json";
        return ApiConnection.createGET(apiUrl).requestSyncCall();
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
