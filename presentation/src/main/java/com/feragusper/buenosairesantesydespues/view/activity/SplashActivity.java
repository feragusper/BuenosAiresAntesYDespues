package com.feragusper.buenosairesantesydespues.view.activity;

import android.os.Bundle;
import android.os.Handler;

import com.feragusper.buenosairesantesydespues.R;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Activity that shows the splash of the application.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(HistoricalRecordListActivity.getCallingIntent(SplashActivity.this));
            }
        }, 2000);
    }

    @Override
    protected int getContentViewResourceId() {
        return R.layout.activity_splash;
    }

}
