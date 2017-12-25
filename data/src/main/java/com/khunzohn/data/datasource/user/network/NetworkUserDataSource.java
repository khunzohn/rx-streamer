package com.khunzohn.data.datasource.user.network;

import com.khunzohn.data.datasource.user.UserDataSource;
import com.khunzohn.data.datasource.user.mapper.UserMapper;
import com.khunzohn.data.entity.UserEntity;
import com.khunzohn.data.exception.DataException;
import com.khunzohn.data.exception.Issue;
import com.khunzohn.data.network.RestAdapter;
import com.khunzohn.domain.model.User;
import io.reactivex.Observable;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Response;

/**
 * Created by khunzohn on 12/25/17.
 */
@Singleton
public class NetworkUserDataSource implements UserDataSource {

  private final UserService userService;
  private final UserMapper userMapper;

  @Inject
  public NetworkUserDataSource(UserMapper userMapper) {
    this.userService = RestAdapter.get().create(UserService.class);
    this.userMapper = userMapper;
  }

  @Override public Observable<List<User>> getUsers() {
    return Observable.defer(() -> {
      try {
        Response<List<UserEntity>> response = userService.getUsers().execute();
        if (response.isSuccessful()) {
          return Observable.just(userMapper.map(response.body()));
        } else {
          return Observable.error(new DataException(Issue.API));
        }
      } catch (IOException e) {
        return Observable.error(new DataException(Issue.NETWORK));
      }
    });
  }
}
