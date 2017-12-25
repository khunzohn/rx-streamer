package com.khunzohn.rxstreamer.feature.news.list;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding2.view.RxView;
import com.khunzohn.rxstreamer.R;
import com.khunzohn.rxstreamer.feature.BaseActivity;
import com.khunzohn.rxstreamer.model.NewsModel;
import com.khunzohn.rxstreamer.util.SingleItemDecoration;
import dagger.android.AndroidInjection;
import javax.inject.Inject;

/**
 * Created by khunzohn on 12/18/17.
 */

public class NewListActivity extends BaseActivity {
  @BindView(R.id.tv_error) TextView tvError;
  @BindView(R.id.progress_bar) ProgressBar progressBar;
  @BindView(R.id.btn_retry) Button btnRetry;
  @BindView(R.id.rv_news) RecyclerView rvNews;
  @BindView(R.id.layout_error) ViewGroup layoutError;

  @Inject
  ViewModelProvider.Factory factory;

  private NewsAdapter newsAdapter;
  private NewListViewModel newListViewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_new_list);
    ButterKnife.bind(this);

    setUpRecyclerView();

    newListViewModel = ViewModelProviders.of(this, factory)
        .get(NewListViewModel.class);

    subscribe();

    if (savedInstanceState == null) {
      newListViewModel.getNews();
    }
  }

  private void subscribe() {
    add(newListViewModel.getNewsModel().subscribe(this::render));

    add(newsAdapter.getEventStream()
        .subscribe(newModel -> Toast.makeText(NewListActivity.this,
            "Goto new detail of " + newModel.title(), Toast.LENGTH_SHORT).show()));

    add(RxView.clicks(btnRetry).subscribe(ignored -> newListViewModel.getNews()));
  }

  private void setUpRecyclerView() {
    int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing_medium);
    SingleItemDecoration decoration = new SingleItemDecoration(spacingInPixels);
    rvNews.addItemDecoration(decoration);
    newsAdapter = new NewsAdapter();
    rvNews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    rvNews.setAdapter(newsAdapter);
  }

  private void render(NewsModel newsModel) {
    layoutError.setVisibility(newsModel.errorVisibility());
    btnRetry.setVisibility(newsModel.retryVisibility());
    rvNews.setVisibility(newsModel.newsVisibility());
    progressBar.setVisibility(newsModel.progressVisibility());

    if (newsModel.retryMessage() != null) {
      tvError.setText(newsModel.retryMessage());
    }

    if (newsModel.errorMessage() != null) {
      tvError.setText(newsModel.errorMessage());
    }

    if (newsModel.newModels() != null) {
      newsAdapter.setNewModels(newsModel.newModels());
    }
  }
}
