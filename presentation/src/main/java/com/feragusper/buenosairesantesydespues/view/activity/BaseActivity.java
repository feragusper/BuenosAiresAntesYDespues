package com.feragusper.buenosairesantesydespues.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.feragusper.buenosairesantesydespues.AndroidApplication;
import com.feragusper.buenosairesantesydespues.R;
import com.feragusper.buenosairesantesydespues.di.components.ApplicationComponent;
import com.feragusper.buenosairesantesydespues.di.modules.ActivityModule;
import com.feragusper.buenosairesantesydespues.navigation.Navigator;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Base {@link Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
        setContentView(getContentViewResourceId());
        ButterKnife.inject(this);
        initializeToolBar();
    }

    protected abstract int getContentViewResourceId();

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

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
