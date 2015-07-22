/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feragusper.buenosairesantesydespues.presenter;

import android.support.annotation.NonNull;

import com.feragusper.buenosairesantesydespues.di.PerActivity;
import com.feragusper.buenosairesantesydespues.domain.HistoricalRecord;
import com.feragusper.buenosairesantesydespues.domain.exception.DefaultErrorBundle;
import com.feragusper.buenosairesantesydespues.domain.exception.ErrorBundle;
import com.feragusper.buenosairesantesydespues.domain.interactor.DefaultSubscriber;
import com.feragusper.buenosairesantesydespues.domain.interactor.UseCase;
import com.feragusper.buenosairesantesydespues.exception.ErrorMessageFactory;
import com.feragusper.buenosairesantesydespues.mapper.HistoricalRecordModelDataMapper;
import com.feragusper.buenosairesantesydespues.model.HistoricalRecordModel;
import com.feragusper.buenosairesantesydespues.view.HistoricalRecordDetailsView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class HistoricalRecordDetailsPresenter implements Presenter {

    /** id used to retrieve user details */
    private int historicalRecordId;

    private HistoricalRecordDetailsView viewDetailsView;

    private final UseCase getUserDetailsUseCase;
    private final HistoricalRecordModelDataMapper historicalRecordModelDataMapper;

    @Inject
    public HistoricalRecordDetailsPresenter(@Named("historicalRecordDetails") UseCase getUserDetailsUseCase,
                                            HistoricalRecordModelDataMapper historicalRecordModelDataMapper) {
        this.getUserDetailsUseCase = getUserDetailsUseCase;
        this.historicalRecordModelDataMapper = historicalRecordModelDataMapper;
    }

    public void setView(@NonNull HistoricalRecordDetailsView view) {
        this.viewDetailsView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getUserDetailsUseCase.unsubscribe();
    }

    /**
     * Initializes the presenter by start retrieving user details.
     */
    public void initialize(int userId) {
        this.historicalRecordId = userId;
        this.loadHistoricalRecordDetails();
    }

    /**
     * Loads user details.
     */
    private void loadHistoricalRecordDetails() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getUserDetails();
    }

    private void showViewLoading() {
        this.viewDetailsView.showLoading();
    }

    private void hideViewLoading() {
        this.viewDetailsView.hideLoading();
    }

    private void showViewRetry() {
        this.viewDetailsView.showRetry();
    }

    private void hideViewRetry() {
        this.viewDetailsView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewDetailsView.getContext(),
                errorBundle.getException());
        this.viewDetailsView.showError(errorMessage);
    }

    private void showUserDetailsInView(HistoricalRecord historicalRecord) {
        final HistoricalRecordModel historicalRecordModel = this.historicalRecordModelDataMapper.transform(historicalRecord);
        this.viewDetailsView.renderHistoricalRecord(historicalRecordModel);
    }

    private void getUserDetails() {
        this.getUserDetailsUseCase.execute(new HistoricalRecordDetailsSubscriber());
    }

    private final class HistoricalRecordDetailsSubscriber extends DefaultSubscriber<HistoricalRecord> {

        @Override
        public void onCompleted() {
            HistoricalRecordDetailsPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            HistoricalRecordDetailsPresenter.this.hideViewLoading();
            HistoricalRecordDetailsPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            HistoricalRecordDetailsPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(HistoricalRecord historicalRecord) {
            HistoricalRecordDetailsPresenter.this.showUserDetailsInView(historicalRecord);
        }
    }
}
