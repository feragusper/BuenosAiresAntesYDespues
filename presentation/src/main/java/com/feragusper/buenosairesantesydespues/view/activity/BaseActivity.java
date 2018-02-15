package com.feragusper.buenosairesantesydespues.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.feragusper.buenosairesantesydespues.AndroidApplication;
import com.feragusper.buenosairesantesydespues.di.components.ApplicationComponent;
import com.feragusper.buenosairesantesydespues.di.modules.ActivityModule;
import com.feragusper.buenosairesantesydespues.navigation.Navigator;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Base {@link Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        onBeforeSetContentView();
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
        setContentView(getContentViewResourceId());
        ButterKnife.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    protected void onBeforeSetContentView() {
        // Do nothing by default
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected abstract int getContentViewResourceId();

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
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
