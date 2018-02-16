package com.feragusper.buenosairesantesydespues.view.activity;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.feragusper.buenosairesantesydespues.BuildConfig;
import com.feragusper.buenosairesantesydespues.R;

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

    @SuppressLint("StringFormatMatches")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isInForeground) {
                    startActivity(HistoricalRecordListActivity.getCallingIntent(SplashActivity.this));
                } else {
                    finish();
                }
            }
        }, 2000);

        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            try {
                PackageInfo packageInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
                mVersionName.setText(getString(R.string.versionName, packageInfo.versionName + " " + BuildConfig.BUILD_TYPE, packageInfo.versionCode));
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(this.getClass().getSimpleName(), "An error has occurred while trying to get the versionName", e);
            }

            mVersionName.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getContentViewResourceId() {
        return R.layout.activity_splash;
    }

}
