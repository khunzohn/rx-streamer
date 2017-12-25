package com.khunzohn.domain.repository;

import com.khunzohn.domain.model.User;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by khunzohn on 12/23/17.
 */

public interface UserRepository {

  Observable<List<User>> getUsers();
}
