package com.khunzohn.data.datasource.user.network;

import com.khunzohn.data.model.response.UserResponse;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by khunzohn on 16/1/18.
 */

public interface NetworkUserDataSource {

  Single<List<UserResponse>> getUsers();
}
