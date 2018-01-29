package com.khunzohn.domain.usecase.base;

import com.khunzohn.domain.exception.base.DomainException;
import com.khunzohn.domain.exception.base.DomainIssue;
import com.khunzohn.domain.exception.base.DomainIssueManager;
import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by khunzohn on 29/1/18.
 */
public abstract class UseCaseWithIssueManager<Action, Result, M extends DomainIssueManager>
    implements ObservableTransformer<Action, Result> {

  private final ThreadExecutor threadExecutor;
  private final PostExecutionThread postExecutionThread;

  private M issueManager;

  public UseCaseWithIssueManager(ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    this.threadExecutor = threadExecutor;
    this.postExecutionThread = postExecutionThread;
    issueManager = provideIssueManager();
  }

  @Override public ObservableSource<Result> apply(Observable<Action> actions) {
    issueManager.closeIssues();
    return actions.flatMap(this::execute)
        .onErrorReturn(this::error)
        .subscribeOn(Schedulers.from(threadExecutor))
        .startWith(progress())
        .observeOn(postExecutionThread.getScheduler());
  }

  @NonNull protected abstract M provideIssueManager();

  protected final void openIssue(DomainIssue domainIssue) {
    issueManager.openIssue(domainIssue);
  }

  protected final DomainException asException() {
    return issueManager.asException();
  }

  protected final boolean hasIssues() {
    return issueManager.hasIssues();
  }

  /**
   * Execute the action
   *
   * @param action Action that triggers the use case operation
   * @return A stream of Result model that represents the fact the operation has succeeded
   */
  public abstract Observable<Result> execute(Action action);

  /**
   * @return Result model that represents the fact that the operation is in progress
   */
  public abstract Result progress();

  /**
   * @param throwable Exception that wraps the reason for failure and will be handled in {@code
   * ModelMapper} of app module
   * @return Result model that represents the fact that the operation has failed
   */
  public abstract Result error(Throwable throwable);
}