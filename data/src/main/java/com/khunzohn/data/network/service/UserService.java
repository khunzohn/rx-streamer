package com.khunzohn.data.network.service;

import com.khunzohn.data.constant.URL;
import com.khunzohn.data.model.response.UserResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by khunzohn on 12/25/17.
 */

public interface UserService {

  @GET(URL.USERS) Call<List<UserResponse>> getUsers();
}
