package com.khunzohn.rxstreamer.di;

import com.khunzohn.rxstreamer.RxApp;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import javax.inject.Singleton;

/**
 * Created by khunzohn on 12/18/17.
 */
@Singleton
@Component(modules = {ViewModelModule.class, AppModule.class, AndroidInjectionModule.class,
    ActivityBuilder.class, RepositoryModule.class})
public interface AppComponent {

  void inject(RxApp app);

  @Component.Builder interface Builder {
    @BindsInstance
    Builder application(RxApp application);

    AppComponent build();
  }
}
