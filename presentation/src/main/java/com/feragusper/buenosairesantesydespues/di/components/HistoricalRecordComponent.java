/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feragusper.buenosairesantesydespues.di.components;

import com.feragusper.buenosairesantesydespues.di.PerActivity;
import com.feragusper.buenosairesantesydespues.di.modules.ActivityModule;
import com.feragusper.buenosairesantesydespues.di.modules.HistoricalRecordModule;
import com.feragusper.buenosairesantesydespues.view.fragment.HistoricalRecordDetailsFragment;
import com.feragusper.buenosairesantesydespues.view.fragment.HistoricalRecordListFragment;

import dagger.Component;

/**
 * A scope {@link com.feragusper.buenosairesantesydespues.di.PerActivity} component.
 * Injects historicalRecord specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, HistoricalRecordModule.class})
public interface HistoricalRecordComponent extends ActivityComponent {
  void inject(HistoricalRecordListFragment historicalRecordListFragment);
  void inject(HistoricalRecordDetailsFragment historicalRecordDetailsFragment);
}
