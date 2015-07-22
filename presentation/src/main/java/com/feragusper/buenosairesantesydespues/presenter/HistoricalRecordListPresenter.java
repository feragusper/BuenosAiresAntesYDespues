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
import com.feragusper.buenosairesantesydespues.view.HistoricalRecordListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class HistoricalRecordListPresenter extends DefaultSubscriber<List<HistoricalRecord>> implements Presenter {

    private HistoricalRecordListView viewListView;

    private final UseCase getUserListUseCase;
    private final HistoricalRecordModelDataMapper userModelDataMapper;

    @Inject
    public HistoricalRecordListPresenter(@Named("historicalRecordList") UseCase getUserListUserCase, HistoricalRecordModelDataMapper userModelDataMapper) {
        this.getUserListUseCase = getUserListUserCase;
        this.userModelDataMapper = userModelDataMapper;
    }

    public void setView(@NonNull HistoricalRecordListView view) {
        this.viewListView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getUserListUseCase.unsubscribe();
    }

    /**
     * Initializes the presenter by start retrieving the user list.
     */
    public void initialize() {
        this.loadUserList();
    }

    /**
     * Loads all users.
     */
    private void loadUserList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getUserList();
    }

    public void onUserClicked(HistoricalRecordModel historicalRecordModel) {
        this.viewListView.viewHistoricalRecord(historicalRecordModel);
    }

    private void showViewLoading() {
        this.viewListView.showLoading();
    }

    private void hideViewLoading() {
        this.viewListView.hideLoading();
    }

    private void showViewRetry() {
        this.viewListView.showRetry();
    }

    private void hideViewRetry() {
        this.viewListView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewListView.getContext(),
                errorBundle.getException());
        this.viewListView.showError(errorMessage);
    }

    private void showUsersCollectionInView(Collection<HistoricalRecord> usersCollection) {
        final Collection<HistoricalRecordModel> userModelsCollection =
                this.userModelDataMapper.transform(usersCollection);
        this.viewListView.renderHistoricalRecordList(userModelsCollection);
    }

    private void getUserList() {
        this.getUserListUseCase.execute(new HistoricalRecordListSubscriber());
    }

    private final class HistoricalRecordListSubscriber extends DefaultSubscriber<List<HistoricalRecord>> {

        @Override
        public void onCompleted() {
            HistoricalRecordListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            HistoricalRecordListPresenter.this.hideViewLoading();
            HistoricalRecordListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            HistoricalRecordListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<HistoricalRecord> historicalRecords) {
            HistoricalRecordListPresenter.this.showUsersCollectionInView(historicalRecords);
        }
    }
}
