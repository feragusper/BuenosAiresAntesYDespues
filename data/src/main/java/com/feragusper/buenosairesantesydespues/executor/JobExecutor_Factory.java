package com.feragusper.buenosairesantesydespues.executor;

import javax.annotation.Generated;

import dagger.internal.Factory;

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

