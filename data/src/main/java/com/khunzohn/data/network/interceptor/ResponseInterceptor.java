package com.khunzohn.data.network.interceptor;

import android.util.Log;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by khunzohn on 10/30/17.
 */

public final class ResponseInterceptor implements Interceptor {

  @Override public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    Response response = chain.proceed(request);
    Log.e("Bus", "intercept " + response.code());
    if (response.code() == 401) {
      HttpErrorEvent.emit(new HttpErrorEvent.Event("", HttpErrorEvent.Type.UNAUTHORIZED));
    }
    if (response.code() == 500) {
      HttpErrorEvent.emit(new HttpErrorEvent.Event("", HttpErrorEvent.Type.SERVER_ERROR));
    }
    return response;
  }
}
