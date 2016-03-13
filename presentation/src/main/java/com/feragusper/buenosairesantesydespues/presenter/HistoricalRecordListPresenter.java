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
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class HistoricalRecordListPresenter extends DefaultSubscriber<List<HistoricalRecord>> implements Presenter {

    private HistoricalRecordListView viewListView;

    private final UseCase getHistoricalRecordListUseCase;
    private final HistoricalRecordModelDataMapper historicalRecordModelDataMapper;
    private Collection<HistoricalRecord> historicalRecords;

    @Inject
    public HistoricalRecordListPresenter(@Named("historicalRecordList") UseCase getHistoricalRecordListUseCase, HistoricalRecordModelDataMapper historicalRecordModelDataMapper) {
        this.getHistoricalRecordListUseCase = getHistoricalRecordListUseCase;
        this.historicalRecordModelDataMapper = historicalRecordModelDataMapper;
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
        getHistoricalRecordListUseCase.unsubscribe();
    }

    /**
     * Initializes the presenter by start retrieving the historicalRecord list.
     */
    public void initialize() {
        this.loadHistoricalRecordList();
    }

    /**
     * Loads all historicalRecords.
     */
    private void loadHistoricalRecordList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getHistoricalRecordList();
    }

    public void onHistoricalRecordClicked(HistoricalRecordModel historicalRecordModel) {
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

    private void showHistoricalRecordsCollectionInView(Collection<HistoricalRecord> historicalRecordsCollection) {
        final Collection<HistoricalRecordModel> historicalRecordModelsCollection =
                this.historicalRecordModelDataMapper.transform(historicalRecordsCollection);
        this.viewListView.renderHistoricalRecordList(historicalRecordModelsCollection);
    }

    private void getHistoricalRecordList() {
        if (historicalRecords == null) {
            this.getHistoricalRecordListUseCase.execute(new HistoricalRecordListSubscriber());
        } else {
            hideViewLoading();
            showHistoricalRecordsCollectionInView(historicalRecords);
        }
    }

    private final class HistoricalRecordListSubscriber extends DefaultSubscriber<List<HistoricalRecord>> {

        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
            showViewRetry();
        }

        @Override
        public void onNext(List<HistoricalRecord> historicalRecords) {
            HistoricalRecordListPresenter.this.historicalRecords = historicalRecords;
            showHistoricalRecordsCollectionInView(historicalRecords);
        }
    }
}
