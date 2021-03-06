package com.feragusper.buenosairesantesydespues.presenter;

import android.support.annotation.NonNull;

import com.feragusper.buenosairesantesydespues.di.PerActivity;
import com.feragusper.buenosairesantesydespues.domain.exception.DefaultErrorBundle;
import com.feragusper.buenosairesantesydespues.domain.exception.ErrorBundle;
import com.feragusper.buenosairesantesydespues.domain.interactor.DefaultSubscriber;
import com.feragusper.buenosairesantesydespues.domain.interactor.GetHistoricalRecordListUseCase;
import com.feragusper.buenosairesantesydespues.domain.model.HistoricalRecord;
import com.feragusper.buenosairesantesydespues.domain.model.HistoricalRecordListPage;
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
    private HistoricalRecordListView view;
    private Collection<HistoricalRecord> historicalRecords = new ArrayList<>();
    private int page;

    @SuppressWarnings("WeakerAccess")
    @Inject
    public HistoricalRecordListPresenter(@Named("historicalRecordList") GetHistoricalRecordListUseCase getHistoricalRecordListUseCase, HistoricalRecordModelDataMapper historicalRecordModelDataMapper) {
        this.getHistoricalRecordListUseCase = getHistoricalRecordListUseCase;
        this.historicalRecordModelDataMapper = historicalRecordModelDataMapper;
    }

    public void setView(@NonNull HistoricalRecordListView view) {
        this.view = view;
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
     * Loads all historicalRecords.
     */
    public void loadHistoricalRecordList() {
        showViewLoading();
        getHistoricalRecordListUseCase.execute(new HistoricalRecordListSubscriber());
    }

    private void showViewLoading() {
        view.showLoading();
    }

    public void reloadHistoricalRecordList() {
        if (historicalRecords.size() > 0) {
            view.renderHistoricalRecordList(historicalRecordModelDataMapper.transform(historicalRecords), page);
        } else {
            loadHistoricalRecordList();
        }
    }

    private void hideViewLoading() {
        view.hideLoading();
    }

    public void onHistoricalRecordClicked(HistoricalRecordModel historicalRecordModel) {
        this.view.viewHistoricalRecord(historicalRecordModel);
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        view.showError(ErrorMessageFactory.create(view.getContext(), errorBundle.getException()));
    }

    public void onLoadMore(int page) {
        this.page = page;
        getHistoricalRecordListUseCase.setPage(page);
        getHistoricalRecordListUseCase.execute(new HistoricalRecordListSubscriber());
    }

    private final class HistoricalRecordListSubscriber extends DefaultSubscriber<HistoricalRecordListPage> {

        @Override
        public void onCompleted() {
            hideViewLoading();
            if (!historicalRecords.isEmpty()) {
                view.hideEmptyListView();
            }
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
            if (historicalRecords.isEmpty()) {
                view.displayEmptyListView();
            }
        }

        @Override
        public void onNext(HistoricalRecordListPage historicalRecordListPage) {
            historicalRecords.addAll(historicalRecordListPage.getHistoricalRecordList());
            view.renderHistoricalRecordList(historicalRecordModelDataMapper.transform(historicalRecordListPage.getHistoricalRecordList()));
            if (historicalRecordListPage.getCountTotal() <= historicalRecords.size()) {
                view.noMorePages();
            }
        }
    }
}
