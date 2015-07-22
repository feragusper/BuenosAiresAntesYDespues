/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.feragusper.buenosairesantesydespues.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.feragusper.buenosairesantesydespues.R;
import com.feragusper.buenosairesantesydespues.di.HasComponent;
import com.feragusper.buenosairesantesydespues.di.components.DaggerHistoricalRecordComponent;
import com.feragusper.buenosairesantesydespues.di.components.HistoricalRecordComponent;
import com.feragusper.buenosairesantesydespues.model.HistoricalRecordModel;
import com.feragusper.buenosairesantesydespues.view.fragment.HistoricalRecordListFragment;

/**
 * Activity that shows a list of HistoricalRecords.
 */
public class HistoricalRecordListActivity extends BaseActivity implements HasComponent<HistoricalRecordComponent>,
        HistoricalRecordListFragment.HistoricalRecordListListener {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HistoricalRecordListActivity.class);
    }

    private HistoricalRecordComponent historicalRecordComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_historical_record_list);

        this.initializeInjector();
    }

    private void initializeInjector() {
        this.historicalRecordComponent = DaggerHistoricalRecordComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public HistoricalRecordComponent getComponent() {
        return historicalRecordComponent;
    }

    @Override
    public void onHistoricalRecordClicked(HistoricalRecordModel historicalRecordModel) {
        this.navigator.navigateToHistoricalRecordDetails(this, historicalRecordModel.getHistoricalRecordId());
    }
}
