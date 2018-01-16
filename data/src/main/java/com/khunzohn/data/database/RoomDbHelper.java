package com.khunzohn.data.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.khunzohn.data.database.dao.UserDao;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by codigo on 8/1/18.
 */

@Singleton
public class RoomDbHelper {

  private final UserDao userDao;

  @Inject
  public RoomDbHelper(Context context) {
    RxDatabase rxDatabase = Room.databaseBuilder(context, RxDatabase.class, "database")
        .fallbackToDestructiveMigration()
        .build();
    userDao = rxDatabase.userDao();
  }

  public UserDao getUserDao() {
    return userDao;
  }
}
