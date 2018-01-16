package com.khunzohn.domain.usecase.base;

import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;
import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by KHUNZOHN on 12/1/18.
 */

public abstract class CompletableUseCase<Action> {
  private final ThreadExecutor threadExecutor;
  private final PostExecutionThread postExecutionThread;

  public CompletableUseCase(ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    this.threadExecutor = threadExecutor;
    this.postExecutionThread = postExecutionThread;
  }

  public Completable execute(Action action) {
    return executeInternal(action).subscribeOn(Schedulers.from(threadExecutor)).observeOn(postExecutionThread.getScheduler());
  }

  protected abstract Completable executeInternal(Action action);
}
