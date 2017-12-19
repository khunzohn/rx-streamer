package com.khunzohn.domain.repository;

import com.khunzohn.domain.model.New;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by khunzohn on 12/17/17.
 */

public interface NewRepository {

    Observable<List<New>> getNews();
}
