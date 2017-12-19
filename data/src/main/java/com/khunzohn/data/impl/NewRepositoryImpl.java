package com.khunzohn.data.impl;

import com.khunzohn.domain.model.New;
import com.khunzohn.domain.repository.NewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * Created by khunzohn on 12/18/17.
 */

public class NewRepositoryImpl implements NewRepository {

    @Override
    public Observable<List<New>> getNews() {
        return Observable.defer(new Callable<ObservableSource<? extends List<New>>>() {
            @Override
            public ObservableSource<? extends List<New>> call() throws Exception {
                List<New> news = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    news.add(New.success(i, "title " + i, "content " + i, "date", "reporter " + 1));
                }
                if (new Random().nextBoolean()) {
                    return Observable.error(new Exception("Random error"));
                }
                return Observable.just(news);
            }
        }).delay(2, TimeUnit.SECONDS);
    }
}
