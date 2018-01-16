package com.khunzohn.domain.usecase;

import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;
import com.khunzohn.domain.model.News;
import com.khunzohn.domain.repository.NewRepository;
import com.khunzohn.domain.usecase.base.UseCase;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * Created by khunzohn on 12/17/17.
 */

public class GetNewsUseCase extends UseCase<GetNewsUseCase.Action, News> {

  private final NewRepository newRepository;

  @Inject
  public GetNewsUseCase(NewRepository newRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.newRepository = newRepository;
  }

  @Override public Observable<News> execute(Action action) {
    return newRepository.getNews().map(News::success);
  }

  @Override public News error(Throwable throwable) {
    return News.error(throwable);
  }

  @Override public News progress() {
    return News.progress();
  }

  public static class Action {

  }
}
