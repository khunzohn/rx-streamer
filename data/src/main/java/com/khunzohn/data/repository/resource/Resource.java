package com.khunzohn.data.repository.resource;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.khunzohn.data.repository.resource.Status.ERROR;
import static com.khunzohn.data.repository.resource.Status.LOADING;
import static com.khunzohn.data.repository.resource.Status.SUCCESS;

/**
 * Modified version of Google's Model State wrapper
 * Created by khunzohn on 11/1/18.
 */

public class Resource<T> {
  @NonNull public final Status status;
  @Nullable public final T data;
  @Nullable public final Throwable error;

  private Resource(@NonNull Status status, @Nullable T data, @Nullable Throwable error) {
    this.status = status;
    this.data = data;
    this.error = error;
  }

  public static <T> Resource<T> success(@NonNull T data) {
    return new Resource<>(SUCCESS, data, null);
  }

  public static <T> Resource<T> error(Throwable error, @Nullable T data) {
    return new Resource<>(ERROR, data, error);
  }

  public static <T> Resource<T> progress(@Nullable T data) {
    return new Resource<>(LOADING, data, null);
  }
}
