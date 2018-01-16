package com.khunzohn.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.khunzohn.data.model.entity.UserEntity;
import io.reactivex.Flowable;
import java.util.List;

/**
 * Created by codigo on 8/1/18.
 */

@Dao
public interface UserDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(UserEntity userEntity);

  @Delete
  void remove(UserEntity userEntity);

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertUsers(List<UserEntity> userEntities);

  @Query("SELECT * FROM users")
  Flowable<List<UserEntity>> getAllUsers();
}
