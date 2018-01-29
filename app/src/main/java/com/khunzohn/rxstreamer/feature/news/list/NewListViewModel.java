package com.khunzohn.rxstreamer.feature.news.list;

import android.util.Log;
import com.khunzohn.domain.model.News;
import com.khunzohn.domain.usecase.GetNewses;
import com.khunzohn.rxstreamer.feature.BaseViewModel;
import com.khunzohn.rxstreamer.mapper.NewsMapper;
import com.khunzohn.rxstreamer.model.NewsModel;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import javax.inject.Inject;

public class NewListViewModel extends BaseViewModel {

  private static final String TAG = NewListViewModel.class.getSimpleName();
  private final NewsMapper newsModelMapper;
  private final GetNewses getNewses;

  private BehaviorSubject<NewsModel> newsModel = BehaviorSubject.create();

  @Inject NewListViewModel(GetNewses getNewses, NewsMapper newsModelMapper) {
    Log.e(TAG, "constructor");
    this.newsModelMapper = newsModelMapper;
    this.getNewses = getNewses;
  }

  void getNews() {
    add(Observable.just(new GetNewses.Action())
        .compose(getNewses)
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
