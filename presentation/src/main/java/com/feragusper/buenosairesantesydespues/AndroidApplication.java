package com.feragusper.buenosairesantesydespues;

import android.app.Application;

import com.feragusper.buenosairesantesydespues.di.components.ApplicationComponent;
import com.feragusper.buenosairesantesydespues.di.components.DaggerApplicationComponent;
import com.feragusper.buenosairesantesydespues.di.modules.ApplicationModule;

/**
 * @author Fernando.Perez
 * @since 0.1
 *
 * Android Main Application
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
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
