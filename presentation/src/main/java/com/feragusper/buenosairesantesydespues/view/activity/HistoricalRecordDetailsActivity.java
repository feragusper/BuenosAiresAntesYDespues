package com.feragusper.buenosairesantesydespues.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;

import com.feragusper.buenosairesantesydespues.R;
import com.feragusper.buenosairesantesydespues.di.HasComponent;
import com.feragusper.buenosairesantesydespues.di.components.DaggerHistoricalRecordComponent;
import com.feragusper.buenosairesantesydespues.di.components.HistoricalRecordComponent;
import com.feragusper.buenosairesantesydespues.di.modules.HistoricalRecordModule;
import com.feragusper.buenosairesantesydespues.view.fragment.HistoricalRecordDetailsFragment;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Activity that shows details of a certain historicalRecord.
 */
public class HistoricalRecordDetailsActivity extends ToolbarActivity implements HasComponent<HistoricalRecordComponent> {

    private static final String INTENT_EXTRA_PARAM_HISTORICAL_RECORD_ID = "com.feragusper.buenosairesantesydespues.INTENT_PARAM_HISTORICAL_RECORD_ID";
    private static final String INSTANCE_STATE_PARAM_HISTORICAL_RECORD_ID = "com.feragusper.buenosairesantesydespues.STATE_PARAM_HISTORICAL_RECORD_ID";

    private String historicalRecordId;
    private HistoricalRecordComponent historicalRecordComponent;

    public static Intent getCallingIntent(Context context, String historicalRecordId) {
        Intent callingIntent = new Intent(context, HistoricalRecordDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_HISTORICAL_RECORD_ID, historicalRecordId);

        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializeActivity(savedInstanceState);
        this.initializeInjector();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(INSTANCE_STATE_PARAM_HISTORICAL_RECORD_ID, this.historicalRecordId);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();    //Call the back button's method
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void initializeToolBar() {
        super.initializeToolBar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }

    @Override
    protected int getContentViewResourceId() {
        return R.layout.activity_historical_record_details;
    }

    /**
     * Initializes this activity.
     */
    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.historicalRecordId = getIntent().getStringExtra(INTENT_EXTRA_PARAM_HISTORICAL_RECORD_ID);
            addFragment(R.id.fl_fragment, HistoricalRecordDetailsFragment.newInstance(this.historicalRecordId));
        } else {
            this.historicalRecordId = savedInstanceState.getString(INSTANCE_STATE_PARAM_HISTORICAL_RECORD_ID);
        }
    }

    private void initializeInjector() {
        this.historicalRecordComponent = DaggerHistoricalRecordComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .historicalRecordModule(new HistoricalRecordModule(this.historicalRecordId))
                .build();
    }

    @Override
    public HistoricalRecordComponent getComponent() {
        return historicalRecordComponent;
    }
}