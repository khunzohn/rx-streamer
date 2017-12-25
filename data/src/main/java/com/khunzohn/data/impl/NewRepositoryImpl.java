package com.khunzohn.data.impl;

import com.khunzohn.data.exception.DataException;
import com.khunzohn.data.exception.Issue;
import com.khunzohn.domain.model.New;
import com.khunzohn.domain.repository.NewRepository;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by khunzohn on 12/18/17.
 */

@Singleton
public class NewRepositoryImpl implements NewRepository {

  @Inject
  public NewRepositoryImpl() {

  }

  @Override
  public Observable<List<New>> getNews() {
    return Observable.defer(new Callable<ObservableSource<? extends List<New>>>() {
      @Override
      public ObservableSource<? extends List<New>> call() throws Exception {
        List<New> news = new ArrayList<>();
        for (Long i = 0L; i < 10; i++) {
          news.add(New.success()
              .id(i)
              .title("")
              .content("")
              .date("")
              .reporter("")
              .build()
          );
        }
        if (new Random().nextBoolean()) {
          return Observable.error(new DataException(Issue.NETWORK));
        }
        return Observable.just(news);
      }
    }).delay(2, TimeUnit.SECONDS);
  }
}
