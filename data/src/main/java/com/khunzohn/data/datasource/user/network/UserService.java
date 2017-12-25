package com.khunzohn.data.datasource.user.network;

import com.khunzohn.data.constant.URL;
import com.khunzohn.data.entity.UserEntity;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by khunzohn on 12/25/17.
 */

public interface UserService {

  @GET(URL.USERS) Call<List<UserEntity>> getUsers();
}
