package com.khunzohn.rxstreamer.di;

import android.content.Context;

import com.khunzohn.data.executor.JobsExecutor;
import com.khunzohn.data.impl.NewRepositoryImpl;
import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;
import com.khunzohn.domain.repository.NewRepository;
import com.khunzohn.rxstreamer.RxApp;
import com.khunzohn.rxstreamer.executor.UiThread;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

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

    @Provides
    @Singleton
    static NewRepository provideNewRepository() {
        return new NewRepositoryImpl();
    }

    @Provides
    @Singleton
    static ThreadExecutor provideThreadExecutor() {
        return new JobsExecutor();
    }

    @Binds
    abstract PostExecutionThread bindPostExecutionThread(UiThread uiThread);
}
