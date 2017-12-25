package com.khunzohn.rxstreamer.executor;

import com.khunzohn.domain.executor.PostExecutionThread;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by khunzohn on 12/19/17.
 */

@Singleton
public class UiThread implements PostExecutionThread {

  @Inject
  public UiThread() {

  }

  @Override
  public Scheduler getScheduler() {
    return AndroidSchedulers.mainThread();
  }
}
