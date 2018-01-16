package com.khunzohn.data.network;

import com.google.gson.Gson;
import com.khunzohn.data.BuildConfig;
import com.khunzohn.data.network.interceptor.HttpLoggingInterceptor;
import com.khunzohn.data.network.interceptor.ResponseInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * App module doesn't know anything about Retrofit thus can't inject from there using dagger.
 *
 *
 * Created by khunzohn on 12/25/17.
 */

public final class RestAdapter {

  private static Retrofit retrofit;

  public static Retrofit get() {
    if (retrofit == null) {
      synchronized (RestAdapter.class) {
        if (retrofit == null) {
          OkHttpClient.Builder httpClient =
              new OkHttpClient.Builder().addInterceptor(new ResponseInterceptor());
          if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(httpLoggingInterceptor);
          }
          retrofit = buildRetrofit(httpClient);
        }
      }
    }
    return retrofit;
  }

  private static Retrofit buildRetrofit(OkHttpClient.Builder httpClient) {
    return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(new Gson()))
        .client(httpClient.build())
        .build();
  }
}
