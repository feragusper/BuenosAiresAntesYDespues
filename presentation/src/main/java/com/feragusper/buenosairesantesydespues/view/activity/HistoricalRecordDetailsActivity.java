/**
 * Copyright (C) 2014 android10.org. All rights reserved.
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
import com.feragusper.buenosairesantesydespues.di.modules.HistoricalRecordModule;
import com.feragusper.buenosairesantesydespues.view.fragment.HistoricalRecordDetailsFragment;

/**
 * Activity that shows details of a certain historicalRecord.
 */
public class HistoricalRecordDetailsActivity extends BaseActivity implements HasComponent<HistoricalRecordComponent> {

  private static final String INTENT_EXTRA_PARAM_USER_ID = "org.android10.INTENT_PARAM_USER_ID";
  private static final String INSTANCE_STATE_PARAM_USER_ID = "org.android10.STATE_PARAM_USER_ID";

  private int historicalRecordId;
  private HistoricalRecordComponent historicalRecordComponent;

  public static Intent getCallingIntent(Context context, int historicalRecordId) {
    Intent callingIntent = new Intent(context, HistoricalRecordDetailsActivity.class);
    callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, historicalRecordId);

    return callingIntent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_user_details);

    this.initializeActivity(savedInstanceState);
    this.initializeInjector();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    if (outState != null) {
      outState.putInt(INSTANCE_STATE_PARAM_USER_ID, this.historicalRecordId);
    }
    super.onSaveInstanceState(outState);
  }

  /**
   * Initializes this activity.
   */
  private void initializeActivity(Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      this.historicalRecordId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_USER_ID, -1);
      addFragment(R.id.fl_fragment, HistoricalRecordDetailsFragment.newInstance(this.historicalRecordId));
    } else {
      this.historicalRecordId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_USER_ID);
    }
  }

  private void initializeInjector() {
    this.historicalRecordComponent = DaggerHistoricalRecordComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .historicalRecordModule(new HistoricalRecordModule(this.historicalRecordId))
        .build();
  }

  @Override public HistoricalRecordComponent getComponent() {
    return historicalRecordComponent;
  }
}
