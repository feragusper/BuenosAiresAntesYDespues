package com.feragusper.buenosairesantesydespues;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.feragusper.buenosairesantesydespues.di.components.ApplicationComponent;
import com.feragusper.buenosairesantesydespues.di.components.DaggerApplicationComponent;
import com.feragusper.buenosairesantesydespues.di.modules.ApplicationModule;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Android Main Application
 */
public class AndroidApplication extends Application {

    private static final int DISPATCH_PERIOD_IN_SECCOD = 30;
    private ApplicationComponent applicationComponent;

    // Analytics
    private GoogleAnalytics analytics;
    private Tracker tracker;
    private static final String TRACKER_ID_PROD = "UA-69017801-2";
    private static final String TRACKER_ID_DEBUG = "UA-69017801-1";

    @Override
    public void onCreate() {
        super.onCreate();

        initializeInjector();
        initializeAnalytics();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }

    private void initializeAnalytics() {
        analytics = GoogleAnalytics.getInstance(this);
        analytics.enableAutoActivityReports(this);
        analytics.setLocalDispatchPeriod(DISPATCH_PERIOD_IN_SECCOD);

        tracker = analytics.newTracker(AndroidApplication.TRACKER_ID_PROD);
        tracker.enableExceptionReporting(true);
        tracker.enableAutoActivityTracking(true);
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
