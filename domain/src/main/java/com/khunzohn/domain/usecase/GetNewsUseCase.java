package com.khunzohn.domain.usecase;

import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;
import com.khunzohn.domain.model.News;
import com.khunzohn.domain.repository.NewRepository;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by khunzohn on 12/17/17.
 */

public final class GetNewsUseCase extends UseCase<GetNewsUseCase.Action, News> {

    private final NewRepository newRepository;

    public GetNewsUseCase(NewRepository newRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.newRepository = newRepository;
    }

    @Override
    public ObservableSource<News> apply(Observable<Action> action) {
        return action.flatMap(act -> newRepository.getNews()
                .map(News::success).onErrorReturn(News::error))
                .subscribeOn(Schedulers.from(getThreadExecutor()))
                .startWith(News.progress())
                .observeOn(getPostExecutionThread().getScheduler());
    }

    public static class Action {

    }
}
