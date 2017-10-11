package com.feragusper.buenosairesantesydespues.di.components;

import android.content.Context;

import com.feragusper.buenosairesantesydespues.di.modules.ApplicationModule;
import com.feragusper.buenosairesantesydespues.domain.executor.PostExecutionThread;
import com.feragusper.buenosairesantesydespues.domain.executor.ThreadExecutor;
import com.feragusper.buenosairesantesydespues.domain.repository.HistoricalRecordRepository;
import com.feragusper.buenosairesantesydespues.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    HistoricalRecordRepository historicalRecordRepository();
}
