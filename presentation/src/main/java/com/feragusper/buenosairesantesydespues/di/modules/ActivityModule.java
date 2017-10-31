package com.feragusper.buenosairesantesydespues.di.modules;

import android.app.Activity;

import com.feragusper.buenosairesantesydespues.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}
