package com.feragusper.buenosairesantesydespues.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
import com.feragusper.buenosairesantesydespues.view.adapter.HistoricalRecordsAdapter;

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

    private HistoricalRecordsAdapter historicalRecordsAdapter;

    private HistoricalRecordListListener historicalRecordListListener;

    public HistoricalRecordListFragment() {
        super();
    }

    @Override
    public void onAttach(Activity activity) {
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
        rv_historicalRecords.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.historical_record_list_columns)));

        historicalRecordsAdapter = new HistoricalRecordsAdapter(getActivity(), new ArrayList<HistoricalRecordModel>());
        historicalRecordsAdapter.setOnItemClickListener(onItemClickListener);
        rv_historicalRecords.setAdapter(historicalRecordsAdapter);
    }

    @Override
    public void showLoading() {
        rl_progress.setVisibility(View.VISIBLE);
        getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        rl_progress.setVisibility(View.GONE);
        getActivity().setProgressBarIndeterminateVisibility(false);
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
    public void renderHistoricalRecordList(Collection<HistoricalRecordModel> historicalRecordModelCollection) {
        if (historicalRecordModelCollection != null) {
            historicalRecordsAdapter.setHistoricalRecordsCollection(historicalRecordModelCollection);
        }
    }

    @Override
    public void viewHistoricalRecord(HistoricalRecordModel historicalRecordModel) {
        if (historicalRecordListListener != null) {
            historicalRecordListListener.onHistoricalRecordClicked(historicalRecordModel);
        }
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
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

    private HistoricalRecordsAdapter.OnItemClickListener onItemClickListener =
            new HistoricalRecordsAdapter.OnItemClickListener() {
                @Override
                public void onHistoricalRecordItemClicked(HistoricalRecordModel historicalRecordModel) {
                    if (historicalRecordListPresenter != null && historicalRecordModel != null) {
                        historicalRecordListPresenter.onHistoricalRecordClicked(historicalRecordModel);
                    }
                }
            };

}
