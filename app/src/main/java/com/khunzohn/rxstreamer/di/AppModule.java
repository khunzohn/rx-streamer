package com.khunzohn.rxstreamer.di;

import android.content.Context;
import com.khunzohn.data.executor.JobsExecutor;
import com.khunzohn.data.network.RestAdapter;
import com.khunzohn.data.network.service.UserService;
import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;
import com.khunzohn.rxstreamer.RxApp;
import com.khunzohn.rxstreamer.executor.UiThread;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by khunzohn on 12/18/17.
 */

@Module
abstract class AppModule {

  @Provides
  @Singleton
  static Context provideContext(RxApp application) {
    return application;
  }

  @Binds
  abstract PostExecutionThread bindPostExecutionThread(UiThread uiThread);

  @Binds
  abstract ThreadExecutor provideThreadExecutor(JobsExecutor jobsExecutor);
}