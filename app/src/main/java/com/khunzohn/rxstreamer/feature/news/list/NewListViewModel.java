package com.khunzohn.rxstreamer.feature.news.list;

import android.util.Log;

import com.khunzohn.domain.model.News;
import com.khunzohn.domain.usecase.GetNewsUseCase;
import com.khunzohn.rxstreamer.feature.BaseViewModel;
import com.khunzohn.rxstreamer.mapper.NewsModelMapper;
import com.khunzohn.rxstreamer.model.NewsModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public final class NewListViewModel extends BaseViewModel {

    private static final String TAG = NewListViewModel.class.getSimpleName();
    private final NewsModelMapper newsModelMapper;
    private final GetNewsUseCase getNewsUseCase;

    private BehaviorSubject<NewsModel> newsModel = BehaviorSubject.create();


    @Inject
    public NewListViewModel(GetNewsUseCase getNewsUseCase, NewsModelMapper newsModelMapper) {
        Log.e(TAG, "constructor");
        this.newsModelMapper = newsModelMapper;
        this.getNewsUseCase = getNewsUseCase;
    }

    void getNews() {
        add(Observable.just(new GetNewsUseCase.Action())
                .compose(getNewsUseCase)
                .map(newsModelMapper::map)
                .subscribe(newsModel::onNext, this::mapErrorAndEmit));
    }

    Observable<NewsModel> getNewsModel() {
        return newsModel;
    }

    private void mapErrorAndEmit(Throwable error) {
        News err = News.error(error);
        newsModel.onNext(newsModelMapper.map(err));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e(TAG, "onCleared");
    }
}
