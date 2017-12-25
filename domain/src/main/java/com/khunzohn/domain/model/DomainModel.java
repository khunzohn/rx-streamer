package com.khunzohn.domain.model;

import io.reactivex.annotations.Nullable;

/**
 * Created by khunzohn on 12/18/17.
 */
public abstract class DomainModel {
  @Nullable
  public abstract Throwable error();

  public abstract State state();

  public enum State {
    PROGRESS, ERROR, SUCCESS
  }
}
