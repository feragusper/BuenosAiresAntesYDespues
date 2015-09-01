package com.feragusper.buenosairesantesydespues.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.feragusper.buenosairesantesydespues.R;

import butterknife.InjectView;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Activity that shows the about of the applciation
 */
public class AboutActivity extends BaseActivity {

    @InjectView(R.id.tv_app_version)
    TextView appVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            appVersion.setText(getString(R.string.version, getPackageManager().getPackageInfo(getPackageName(), 0).versionName));
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(this.getClass().getSimpleName(), "An error ocurred while trying to get the version name", e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    protected int getContentViewResourceId() {
        return R.layout.activity_about;
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, AboutActivity.class);
    }
}
