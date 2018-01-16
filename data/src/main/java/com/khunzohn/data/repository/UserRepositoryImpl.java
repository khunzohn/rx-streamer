package com.khunzohn.data.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.khunzohn.data.datasource.user.local.LocalUserDataSource;
import com.khunzohn.data.datasource.user.mapper.UserEntityMapper;
import com.khunzohn.data.datasource.user.mapper.UserResponseMapper;
import com.khunzohn.data.datasource.user.network.NetworkUserDataSource;
import com.khunzohn.data.model.entity.UserEntity;
import com.khunzohn.data.model.response.UserResponse;
import com.khunzohn.data.repository.resource.NetworkBoundResource;
import com.khunzohn.data.repository.resource.Resource;
import com.khunzohn.domain.model.User;
import com.khunzohn.domain.model.Users;
import com.khunzohn.domain.repository.UserRepository;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by khunzohn on 12/25/17.
 */

@Singleton
public class UserRepositoryImpl implements UserRepository {

  private final NetworkUserDataSource networkDataSource;
  private final LocalUserDataSource localUserDataSource;
  private final UserResponseMapper userResponseMapper;
  private final UserEntityMapper userEntityMapper;

  @Inject public UserRepositoryImpl(NetworkUserDataSource networkDataSource,
      LocalUserDataSource localUserDataSource, UserResponseMapper userResponseMapper,
      UserEntityMapper userEntityMapper) {
    this.networkDataSource = networkDataSource;
    this.userEntityMapper = userEntityMapper;
    this.userResponseMapper = userResponseMapper;
    this.localUserDataSource = localUserDataSource;
  }

  @Override public Flowable<Users> streamUsers() {
    return new NetworkBoundResource<List<UserEntity>, List<UserResponse>>() {

      @Override protected void saveCallResult(@NonNull List<UserResponse> item) {
        localUserDataSource.addUsers(userResponseMapper.mapToEntities(item));
      }

      @Override protected boolean shouldFetch(@Nullable List<UserEntity> data) {
        return data == null || data.size() == 0;
      }

      @NonNull @Override protected Flowable<List<UserEntity>> loadFromDb() {
        return localUserDataSource.getUsers();
      }

      @NonNull @Override protected Single<List<UserResponse>> createCall() {
        return networkDataSource.getUsers();
      }
    }.getAsFlowable().map(this::transform);
  }

  @Override public Completable addUser(User user) {
    return Completable.fromAction(
        () -> localUserDataSource.addUser(userEntityMapper.reverse(user)));
  }

  private Users transform(Resource<List<UserEntity>> resource) {
    switch (resource.status) {
      case ERROR:
        return Users.error(resource.error, userEntityMapper.map(resource.data));
      case LOADING:
        return Users.progress(userEntityMapper.map(resource.data));
      case SUCCESS:
        return Users.success(userEntityMapper.map(resource.data));
      default:
        return Users.progress(null);
    }
  }
}
