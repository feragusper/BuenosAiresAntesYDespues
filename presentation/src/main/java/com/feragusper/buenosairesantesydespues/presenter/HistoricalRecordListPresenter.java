package com.feragusper.buenosairesantesydespues.presenter;

import android.support.annotation.NonNull;

import com.feragusper.buenosairesantesydespues.di.PerActivity;
import com.feragusper.buenosairesantesydespues.domain.HistoricalRecord;
import com.feragusper.buenosairesantesydespues.domain.exception.DefaultErrorBundle;
import com.feragusper.buenosairesantesydespues.domain.exception.ErrorBundle;
import com.feragusper.buenosairesantesydespues.domain.interactor.DefaultSubscriber;
import com.feragusper.buenosairesantesydespues.domain.interactor.GetHistoricalRecordListUseCase;
import com.feragusper.buenosairesantesydespues.exception.ErrorMessageFactory;
import com.feragusper.buenosairesantesydespues.mapper.HistoricalRecordModelDataMapper;
import com.feragusper.buenosairesantesydespues.model.HistoricalRecordModel;
import com.feragusper.buenosairesantesydespues.view.HistoricalRecordListView;

import java.util.ArrayList;
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

    private final GetHistoricalRecordListUseCase getHistoricalRecordListUseCase;
    private final HistoricalRecordModelDataMapper historicalRecordModelDataMapper;
    private HistoricalRecordListView viewListView;
    private Collection<HistoricalRecord> historicalRecords = new ArrayList<>();
    private int page;

    @SuppressWarnings("WeakerAccess")
    @Inject
    public HistoricalRecordListPresenter(@Named("historicalRecordList") GetHistoricalRecordListUseCase getHistoricalRecordListUseCase, HistoricalRecordModelDataMapper historicalRecordModelDataMapper) {
        this.getHistoricalRecordListUseCase = getHistoricalRecordListUseCase;
        this.historicalRecordModelDataMapper = historicalRecordModelDataMapper;
    }

    private final class HistoricalRecordListSubscriber extends DefaultSubscriber<List<HistoricalRecord>> {

        @Override
        public void onCompleted() {
            hideViewLoading();
            if (!historicalRecords.isEmpty()) {
                viewListView.hideEmptyListView();
            }
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
            if (historicalRecords.isEmpty()) {
                viewListView.displayEmptyListView();
            }
        }

        @Override
        public void onNext(List<HistoricalRecord> historicalRecords) {
            HistoricalRecordListPresenter.this.historicalRecords.addAll(historicalRecords);
            showHistoricalRecordsCollectionInView(historicalRecords);
        }
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
    public void loadHistoricalRecordList() {
        this.showViewLoading();
        this.getHistoricalRecordList();
    }

    private void showViewLoading() {
        viewListView.showLoading();
    }

    private void getHistoricalRecordList() {
        if (historicalRecords.isEmpty()) {
            this.getHistoricalRecordListUseCase.execute(new HistoricalRecordListSubscriber());
        } else {
            hideViewLoading();
            showHistoricalRecordsCollectionInView(historicalRecords);
        }
    }

    private void hideViewLoading() {
        viewListView.hideLoading();
    }

    private void showHistoricalRecordsCollectionInView(Collection<HistoricalRecord> historicalRecordsCollection) {
        viewListView.renderHistoricalRecordList(historicalRecordModelDataMapper.transform(historicalRecordsCollection), page);
    }

    public void onHistoricalRecordClicked(HistoricalRecordModel historicalRecordModel) {
        this.viewListView.viewHistoricalRecord(historicalRecordModel);
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        viewListView.showError(ErrorMessageFactory.create(viewListView.getContext(), errorBundle.getException()));
    }

    public void onLoadMore(int page) {
        this.page = page;
        getHistoricalRecordListUseCase.setPage(page);
        getHistoricalRecordListUseCase.execute(new HistoricalRecordListSubscriber());
    }
}
