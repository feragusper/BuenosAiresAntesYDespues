package com.feragusper.buenosairesantesydespues.mapper;

import com.feragusper.buenosairesantesydespues.entity.mapper.HistoricalRecordEntityDataMapper;

import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public enum UserEntityDataMapper_Factory implements Factory<HistoricalRecordEntityDataMapper> {
INSTANCE;

  @Override
  public HistoricalRecordEntityDataMapper get() {
    return new HistoricalRecordEntityDataMapper();
  }

  public static Factory<HistoricalRecordEntityDataMapper> create() {
    return INSTANCE;
  }
}

