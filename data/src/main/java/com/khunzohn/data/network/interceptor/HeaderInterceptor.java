package com.khunzohn.data.network.interceptor;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by khunzohn on 11/14/17.
 */

public final class HeaderInterceptor implements Interceptor {

  private static final String BEARER = "bearer";
  private static final String TAG = HeaderInterceptor.class.getSimpleName();


  @Override public Response intercept(Chain chain) throws IOException {

    Request request = chain.request();

    /*if (user != null) {
      final String token = BEARER + user.apiToken();
      Log.e(TAG,"token="+token);
      request = request.newBuilder().addHeader("Authorization", token).build();
    }
*/
    return chain.proceed(request);
  }
}
