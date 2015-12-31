package com.feragusper.buenosairesantesydespues.di.components;

import com.feragusper.buenosairesantesydespues.di.PerActivity;
import com.feragusper.buenosairesantesydespues.di.modules.ActivityModule;
import com.feragusper.buenosairesantesydespues.di.modules.HistoricalRecordModule;
import com.feragusper.buenosairesantesydespues.view.fragment.HistoricalRecordDetailsFragment;
import com.feragusper.buenosairesantesydespues.view.fragment.HistoricalRecordListFragment;

import dagger.Component;

/**
 * @author Fernando.Perez
 * @since 0.1
 *
 * A scope {@link com.feragusper.buenosairesantesydespues.di.PerActivity} component.
 * Injects historicalRecord specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, HistoricalRecordModule.class})
public interface HistoricalRecordComponent extends ActivityComponent {

    void inject(HistoricalRecordListFragment historicalRecordListFragment);

    void inject(HistoricalRecordDetailsFragment historicalRecordDetailsFragment);

}
