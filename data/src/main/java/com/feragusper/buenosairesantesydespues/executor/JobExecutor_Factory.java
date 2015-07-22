package com.feragusper.buenosairesantesydespues.executor;

import com.feragusper.buenosairesantesydespues.executor.JobExecutor;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public enum JobExecutor_Factory implements Factory<JobExecutor> {
INSTANCE;

  @Override
  public JobExecutor get() {  
    return new JobExecutor();
  }

  public static Factory<JobExecutor> create() {  
    return INSTANCE;
  }
}

