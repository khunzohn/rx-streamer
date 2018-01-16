package com.khunzohn.data.datasource.user.local;

import com.khunzohn.data.database.RoomDbHelper;
import com.khunzohn.data.database.dao.UserDao;
import com.khunzohn.data.model.entity.UserEntity;
import io.reactivex.Flowable;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by khunzohn on 8/1/18.
 */

public class LocalUserDataSourceImpl implements LocalUserDataSource {

  private final UserDao userDao;

  @Inject public LocalUserDataSourceImpl(RoomDbHelper roomDbHelper) {
    this.userDao = roomDbHelper.getUserDao();
  }

  @Override
  public Flowable<List<UserEntity>> getUsers() {
    return userDao.getAllUsers();
  }

  @Override
  public void addUsers(List<UserEntity> userEntities) {
    userDao.insertUsers(userEntities);
  }

  @Override
  public void addUser(UserEntity user) {
    userDao.insert(user);
  }
}
