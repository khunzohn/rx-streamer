package com.khunzohn.domain.repository;

import com.khunzohn.domain.model.User;
import com.khunzohn.domain.model.Users;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by khunzohn on 12/23/17.
 */

public interface UserRepository {

  Flowable<Users> streamUsers();

  Completable addUser(User user);

}
