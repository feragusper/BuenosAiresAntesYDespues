package com.feragusper.buenosairesantesydespues.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.feragusper.buenosairesantesydespues.R;

import butterknife.InjectView;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Base {@link Activity} class for every Activity in this application.
 */
public abstract class ToolbarActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeToolBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.base_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_rate:
                navigator.navigateToPlayStore(this);
                return true;
            case R.id.action_about:
                navigator.navigateToAbout(this);
                return true;
            case R.id.action_send_feedback:
                navigator.navigateToSendFeedback(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @CallSuper
    protected void initializeToolBar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }
}
