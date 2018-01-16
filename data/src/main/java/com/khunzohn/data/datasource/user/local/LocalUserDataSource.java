package com.khunzohn.data.datasource.user.local;

import com.khunzohn.data.model.entity.UserEntity;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by khunzohn on 16/1/18.
 */

public interface LocalUserDataSource {

  Flowable<List<UserEntity>> getUsers();

  void addUsers(List<UserEntity> userEntities);

  void addUser(UserEntity user);
}
