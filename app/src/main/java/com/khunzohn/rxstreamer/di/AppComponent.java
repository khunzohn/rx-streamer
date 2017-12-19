package com.khunzohn.rxstreamer.di;

import com.khunzohn.rxstreamer.RxApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by khunzohn on 12/18/17.
 */
@Singleton
@Component(modules = {ViewModelModule.class, AppModule.class, AndroidInjectionModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(RxApp application);

        AppComponent build();
    }

    void inject(RxApp app);
}
