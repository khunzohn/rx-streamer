package com.khunzohn.domain.repository;

import com.khunzohn.domain.model.New;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by khunzohn on 12/17/17.
 */

public interface NewRepository {

  Observable<List<New>> getNews();
}
