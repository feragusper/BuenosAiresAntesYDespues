package com.feragusper.buenosairesantesydespues.view.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.feragusper.buenosairesantesydespues.BuildConfig;
import com.feragusper.buenosairesantesydespues.R;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Activity that shows the splash of the application.
 */
public class SplashActivity extends BaseActivity {

    @InjectView(R.id.tv_versionName)
    TextView mVersionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(HistoricalRecordListActivity.getCallingIntent(SplashActivity.this));
            }
        }, 2000);

        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            mVersionName.setText(getString(R.string.versionName, pInfo.versionName + " " + BuildConfig.BUILD_TYPE));
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(this.getClass().getSimpleName(), "An error has occurred while trying to get the versionName", e);
        }
    }

    @Override
    protected int getContentViewResourceId() {
        return R.layout.activity_splash;
    }

}
