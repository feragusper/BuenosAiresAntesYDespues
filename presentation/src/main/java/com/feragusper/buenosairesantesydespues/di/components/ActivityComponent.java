package com.feragusper.buenosairesantesydespues.di.components;

import android.app.Activity;

import com.feragusper.buenosairesantesydespues.di.PerActivity;
import com.feragusper.buenosairesantesydespues.di.modules.ActivityModule;

import dagger.Component;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * <p>
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link com.feragusper.buenosairesantesydespues.di.PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}
