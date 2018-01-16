package com.khunzohn.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.khunzohn.data.database.dao.UserDao;
import com.khunzohn.data.model.entity.UserEntity;

/**
 * Created by codigo on 8/1/18.
 */

@Database(entities = UserEntity.class, version = 1)
public abstract class RxDatabase extends RoomDatabase {

  public abstract UserDao userDao();
}
