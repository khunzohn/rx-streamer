package com.khunzohn.rxstreamer.di;

import com.khunzohn.data.repository.NewRepositoryImpl;
import com.khunzohn.data.repository.UserRepositoryImpl;
import com.khunzohn.domain.repository.NewRepository;
import com.khunzohn.domain.repository.UserRepository;
import dagger.Binds;
import dagger.Module;

/**
 * Created by khunzohn on 12/25/17.
 */

@Module
abstract class RepositoryModule {

  @Binds
  abstract NewRepository provideNewRepository(NewRepositoryImpl newRepository);

  @Binds
  abstract UserRepository provideUserRepository(UserRepositoryImpl userRepository);
}
