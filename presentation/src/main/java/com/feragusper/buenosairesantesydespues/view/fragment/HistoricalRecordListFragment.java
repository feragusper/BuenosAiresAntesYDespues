package com.feragusper.buenosairesantesydespues.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.feragusper.buenosairesantesydespues.R;
import com.feragusper.buenosairesantesydespues.di.components.HistoricalRecordComponent;
import com.feragusper.buenosairesantesydespues.model.HistoricalRecordModel;
import com.feragusper.buenosairesantesydespues.presenter.HistoricalRecordListPresenter;
import com.feragusper.buenosairesantesydespues.view.HistoricalRecordListView;
import com.feragusper.buenosairesantesydespues.view.adapter.HistoricalRecordListAdapter;
import com.feragusper.buenosairesantesydespues.view.widget.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Fragment that shows details of a certain historical record.
 */
public class HistoricalRecordListFragment extends BaseFragment implements HistoricalRecordListView {

    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;

    /**
     * Interface for listening historicalRecord list events.
     */
    public interface HistoricalRecordListListener {
        void onHistoricalRecordClicked(final HistoricalRecordModel historicalRecordModel);
    }

    @Inject
    HistoricalRecordListPresenter historicalRecordListPresenter;

    @InjectView(R.id.rv_historicalRecords)
    RecyclerView rv_historicalRecords;
    @InjectView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @InjectView(R.id.rl_retry)
    RelativeLayout rl_retry;
    @InjectView(R.id.bt_retry)
    Button bt_retry;
    @InjectView(R.id.coordinatorLayout)
    CoordinatorLayout cl_coordinatorLayout;

    private HistoricalRecordListAdapter historicalRecordListAdapter;

    private HistoricalRecordListListener historicalRecordListListener;

    public HistoricalRecordListFragment() {
        super();
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        if (activity instanceof HistoricalRecordListListener) {
            historicalRecordListListener = (HistoricalRecordListListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_historical_record_list, container, true);
        ButterKnife.inject(this, fragmentView);
        setupUI();

        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        loadHistoricalRecordList();
    }

    @Override
    public void onResume() {
        super.onResume();
        historicalRecordListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        historicalRecordListPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        historicalRecordListPresenter.destroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private void initialize() {
        if (historicalRecordListPresenter == null) {
            getComponent(HistoricalRecordComponent.class).inject(this);
            historicalRecordListPresenter.setView(this);
        }
    }

    private void setupUI() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.historical_record_list_columns));
        rv_historicalRecords.setLayoutManager(gridLayoutManager);

        historicalRecordListAdapter = new HistoricalRecordListAdapter(getActivity(), new ArrayList<HistoricalRecordModel>());
        historicalRecordListAdapter.setOnItemClickListener(onItemClickListener);
        rv_historicalRecords.setAdapter(historicalRecordListAdapter);
        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                historicalRecordListPresenter.onLoadMore(page);
            }
        };
        rv_historicalRecords.setOnScrollListener(endlessRecyclerViewScrollListener);
    }

    @Override
    public void showLoading() {
        rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void renderHistoricalRecordList(Collection<HistoricalRecordModel> historicalRecordModelCollection, int page) {
        if (historicalRecordModelCollection != null) {
            historicalRecordListAdapter.setHistoricalRecordsCollection(historicalRecordModelCollection);
        }
//        endlessRecyclerViewScrollListener.setPage(page);
    }

    @Override
    public void viewHistoricalRecord(HistoricalRecordModel historicalRecordModel) {
        if (historicalRecordListListener != null) {
            historicalRecordListListener.onHistoricalRecordClicked(historicalRecordModel);
        }
    }

    @Override
    public void showError(String message) {
        Snackbar snackbar = Snackbar
                .make(cl_coordinatorLayout, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        historicalRecordListPresenter.onLoadMore(endlessRecyclerViewScrollListener.getCurrentPage());
                    }
                });
        snackbar.show();
//        showToastMessage(message);
    }

    @SuppressLint("Override")
    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    /**
     * Loads all historicalRecords.
     */
    private void loadHistoricalRecordList() {
        historicalRecordListPresenter.initialize();
    }

    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        loadHistoricalRecordList();
    }

    private HistoricalRecordListAdapter.OnItemClickListener onItemClickListener =
            new HistoricalRecordListAdapter.OnItemClickListener() {
                @Override
                public void onHistoricalRecordItemClicked(HistoricalRecordModel historicalRecordModel) {
                    if (historicalRecordListPresenter != null && historicalRecordModel != null) {
                        historicalRecordListPresenter.onHistoricalRecordClicked(historicalRecordModel);
                    }
                }
            };

}
