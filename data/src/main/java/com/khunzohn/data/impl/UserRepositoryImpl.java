package com.khunzohn.data.impl;

import com.khunzohn.data.datasource.user.network.NetworkUserDataSource;
import com.khunzohn.domain.model.User;
import com.khunzohn.domain.repository.UserRepository;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by khunzohn on 12/25/17.
 */

@Singleton
public class UserRepositoryImpl implements UserRepository {

  private final NetworkUserDataSource networkDataSource;

  @Inject
  public UserRepositoryImpl(NetworkUserDataSource networkDataSource) {
    this.networkDataSource = networkDataSource;
  }

  @Override public Observable<List<User>> getUsers() {
    return networkDataSource.getUsers();
  }
}
