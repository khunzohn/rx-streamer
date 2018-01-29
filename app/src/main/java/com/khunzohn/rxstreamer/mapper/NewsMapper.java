package com.khunzohn.rxstreamer.mapper;

import android.content.Context;
import android.view.View;
import com.khunzohn.data.exception.DataException;
import com.khunzohn.domain.model.News;
import com.khunzohn.rxstreamer.R;
import com.khunzohn.rxstreamer.model.NewModel;
import com.khunzohn.rxstreamer.model.NewsModel;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by khunzohn on 12/18/17.
 */

public class NewsMapper extends ViewStateMapper<NewsModel, News> {

  private final NewMapper newModelMapper;

  @Inject
  public NewsMapper(Context context, NewMapper newModelMapper) {
    super(context);
    this.newModelMapper = newModelMapper;
  }

  @Override
  public NewsModel map(News domainModel) {
    int retryVisibility = View.INVISIBLE;
    int progressVisibility = View.GONE;
    int errorVisibility = View.GONE;
    int newsVisibility = View.GONE;
    String retryMessage = null;
    String errorMessage = null;
    List<NewModel> newModels = null;

    switch (domainModel.state()) {
      case PROGRESS:
        progressVisibility = View.VISIBLE;
        break;
      case SUCCESS:
        newsVisibility = View.VISIBLE;
        newModels = newModelMapper.map(domainModel.news());
        break;
      case ERROR:
        errorVisibility = View.VISIBLE;
        Throwable error = domainModel.error();
        if (error instanceof DataException) {
          DataException de = (DataException) error;
          if (de.shouldRetry()) {
            retryMessage = getContext().getString(R.string.error_fetch_news);
            retryVisibility = View.VISIBLE;
          } else {
            errorMessage = getContext().getString(R.string.error_fetch_news);
          }
        } else {
          errorMessage = getContext().getString(R.string.error_fetch_news);
        }
        break;
    }

    return NewsModel.builder()
        .newsVisibility(newsVisibility)
        .retryVisibility(retryVisibility)
        .progressVisibility(progressVisibility)
        .errorVisibility(errorVisibility)
        .retryMessage(retryMessage)
        .errorMessage(errorMessage)
        .newModels(newModels)
        .build();
  }
}
