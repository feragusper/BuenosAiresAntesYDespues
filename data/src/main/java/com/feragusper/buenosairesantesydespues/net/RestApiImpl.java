/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {

  private final Context context;
  private final HistoricalRecordEntityJsonMapper historicalRecordEntityJsonMapper;

  /**
   * Constructor of the class
   *
   * @param context {@link android.content.Context}.
   * @param historicalRecordEntityJsonMapper {@link HistoricalRecordEntityJsonMapper}.
   */
  public RestApiImpl(Context context, HistoricalRecordEntityJsonMapper historicalRecordEntityJsonMapper) {
    if (context == null || historicalRecordEntityJsonMapper == null) {
      throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
    }
    this.context = context.getApplicationContext();
    this.historicalRecordEntityJsonMapper = historicalRecordEntityJsonMapper;
  }

  @Override public Observable<List<HistoricalRecordEntity>> getHistoricalRecordEntityList() {
    return Observable.create(new Observable.OnSubscribe<List<HistoricalRecordEntity>>() {
      @Override public void call(Subscriber<? super List<HistoricalRecordEntity>> subscriber) {

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

  @Override public Observable<HistoricalRecordEntity> getHistoricalRecordEntityById(final int historicalRecordId) {
    return Observable.create(new Observable.OnSubscribe<HistoricalRecordEntity>() {
      @Override public void call(Subscriber<? super HistoricalRecordEntity> subscriber) {

        if (isThereInternetConnection()) {
          try {
            String responseHistoricalRecordDetails = getHistoricalRecordDetailsFromApi(historicalRecordId);
            if (responseHistoricalRecordDetails != null) {
              subscriber.onNext(historicalRecordEntityJsonMapper.transformHistoricalRecordEntity(responseHistoricalRecordDetails));
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

    ConnectivityManager connectivityManager =
        (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

    return isConnected;
  }
}
