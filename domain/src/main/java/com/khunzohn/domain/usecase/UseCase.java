package com.khunzohn.domain.usecase;

import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by khunzohn on 12/17/17.
 */

public abstract class UseCase<Action, Result>
    implements ObservableTransformer<Action, Result> {

  private final ThreadExecutor threadExecutor;
  private final PostExecutionThread postExecutionThread;

  UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    this.threadExecutor = threadExecutor;
    this.postExecutionThread = postExecutionThread;
  }

  @Override public ObservableSource<Result> apply(Observable<Action> actions) {
    return actions.flatMap(this::execute)
        .onErrorReturn(this::error)
        .subscribeOn(Schedulers.from(threadExecutor))
        .startWith(progress())
        .observeOn(postExecutionThread.getScheduler());
  }

  /**
   * Execute the action
   * @param action Action that triggers the use case operation
   * @return A stream of Result model that represents the fact the operation has succeeded
   */
  public abstract Observable<Result> execute(Action action);

  /**
   *
   * @return Result model that represents the fact that the operation is in progress
   */
  public abstract Result progress();

  /**
   *
   * @param throwable Exception that wraps the reason for failure and will be handled in {@code ModelMapper} of app module
   * @return Result model that represents the fact that the operation has failed
   */
  public abstract Result error(Throwable throwable);
}
