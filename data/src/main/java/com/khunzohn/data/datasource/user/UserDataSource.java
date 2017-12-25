package com.khunzohn.data.datasource.user;

import com.khunzohn.domain.model.User;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by khunzohn on 12/25/17.
 */

public interface UserDataSource {

  Observable<List<User>> getUsers();
}
