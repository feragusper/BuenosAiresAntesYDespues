package com.feragusper.buenosairesantesydespues.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

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

    //region Properties
    private static final String INTENT_EXTRA_PARAM_HISTORICAL_RECORD_ID = "com.feragusper.buenosairesantesydespues.INTENT_PARAM_HISTORICAL_RECORD_ID";
    private static final String INSTANCE_STATE_PARAM_HISTORICAL_RECORD_ID = "com.feragusper.buenosairesantesydespues.STATE_PARAM_HISTORICAL_RECORD_ID";

    private String historicalRecordId;
    private HistoricalRecordComponent historicalRecordComponent;
    //endregion

    //region Public Static Implementation
    public static Intent getCallingIntent(Context context, String historicalRecordId) {
        Intent callingIntent = new Intent(context, HistoricalRecordDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_HISTORICAL_RECORD_ID, historicalRecordId);

        return callingIntent;
    }
    //endregion

    //region Activity Implementation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initializeActivity(savedInstanceState);
        this.initializeInjector();
    }

    //region ToolbarActivity Implementation
    @SuppressWarnings("ConstantConditions")
    @Override
    protected void initializeToolBar() {
        super.initializeToolBar();

        if (toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_action_arrow_back));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();    //Call the back button's method
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region Private Implementation
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
    //endregion

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(INSTANCE_STATE_PARAM_HISTORICAL_RECORD_ID, this.historicalRecordId);
        }
        super.onSaveInstanceState(outState);
    }
    //endregion

    //region BaseActivity Implementation
    @Override
    protected void onBeforeSetContentView() {
        //noinspection deprecation
        if (getWindowManager().getDefaultDisplay().getWidth() > getWindowManager().getDefaultDisplay().getHeight()) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
    //endregion

    @Override
    protected int getContentViewResourceId() {
        return R.layout.activity_historical_record_details;
    }

    //region HasComponent Implementation
    @Override
    public HistoricalRecordComponent getComponent() {
        return historicalRecordComponent;
    }
    //endregion

}