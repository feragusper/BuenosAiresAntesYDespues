package com.feragusper.buenosairesantesydespues;

import android.app.Application;

import com.feragusper.buenosairesantesydespues.di.components.ApplicationComponent;
import com.feragusper.buenosairesantesydespues.di.components.DaggerApplicationComponent;
import com.feragusper.buenosairesantesydespues.di.modules.ApplicationModule;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Android Main Application
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;
    private GoogleAnalytics analytics;
    private Tracker tracker;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeInjector();
        initializeAnalytics();
    }

    private void initializeAnalytics() {
        analytics = GoogleAnalytics.getInstance(this);
        analytics.enableAutoActivityReports(this);
        analytics.setLocalDispatchPeriod(1800);

        tracker = analytics.newTracker("UA-12804695-23");
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);
        tracker.setScreenName("Testing");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
