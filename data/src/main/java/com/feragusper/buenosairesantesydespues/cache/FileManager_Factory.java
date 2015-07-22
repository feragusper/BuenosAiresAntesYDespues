package com.fernandocejas.android10.sample.data.cache;

import com.feragusper.buenosairesantesydespues.cache.FileManager;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public enum FileManager_Factory implements Factory<FileManager> {
INSTANCE;

  @Override
  public FileManager get() {  
    return new FileManager();
  }

  public static Factory<FileManager> create() {  
    return INSTANCE;
  }
}

