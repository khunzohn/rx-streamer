package com.khunzohn.data.datasource.user.network;

import com.khunzohn.data.exception.DataException;
import com.khunzohn.data.exception.Issue;
import com.khunzohn.data.model.response.UserResponse;
import com.khunzohn.data.network.RestAdapter;
import com.khunzohn.data.network.service.UserService;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Response;

/**
 * Created by khunzohn on 12/25/17.
 */
@Singleton
public class NetworkUserDataSourceImpl implements NetworkUserDataSource{

  private final UserService userService;

  @Inject
  public NetworkUserDataSourceImpl() {
    this.userService = RestAdapter.get().create(UserService.class);
  }

  @Override
  public Single<List<UserResponse>> getUsers() {
    return Single.defer(() -> {
      try {
        Response<List<UserResponse>> response = userService.getUsers().execute();
        if (response.isSuccessful()) {
          return Single.just(response.body());
        } else {
          return Single.error(new DataException(Issue.API));
        }
      } catch (IOException e) {
        return Single.error(new DataException(Issue.NETWORK));
      }
    });
  }
}
