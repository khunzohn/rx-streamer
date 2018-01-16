package com.khunzohn.rxstreamer.mapper;

import android.content.Context;

/**
 * Created by khunzohn on 12/18/17.
 */

public abstract class DomainMapper<V, D> {

  private final Context context;

  public DomainMapper(Context context) {
    this.context = context;
  }

  public abstract V map(D domainModel);

  public Context getContext() {
    return context;
  }
}
