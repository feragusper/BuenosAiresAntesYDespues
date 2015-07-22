package com.fernandocejas.android10.sample.data.cache.serializer;

import com.feragusper.buenosairesantesydespues.cache.serializer.JsonSerializer;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public enum JsonSerializer_Factory implements Factory<JsonSerializer> {
INSTANCE;

  @Override
  public JsonSerializer get() {  
    return new JsonSerializer();
  }

  public static Factory<JsonSerializer> create() {
    return INSTANCE;
  }
}

