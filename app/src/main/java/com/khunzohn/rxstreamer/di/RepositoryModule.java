package com.khunzohn.rxstreamer.di;

import com.khunzohn.data.datasource.user.local.LocalUserDataSource;
import com.khunzohn.data.datasource.user.local.LocalUserDataSourceImpl;
import com.khunzohn.data.datasource.user.network.NetworkUserDataSource;
import com.khunzohn.data.datasource.user.network.NetworkUserDataSourceImpl;
import com.khunzohn.data.network.RestAdapter;
import com.khunzohn.data.network.service.UserService;
import com.khunzohn.data.repository.NewRepositoryImpl;
import com.khunzohn.data.repository.UserRepositoryImpl;
import com.khunzohn.domain.repository.NewRepository;
import com.khunzohn.domain.repository.UserRepository;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by khunzohn on 12/25/17.
 */

@Module
abstract class RepositoryModule {

  @Binds
  abstract NewRepository provideNewRepository(NewRepositoryImpl newRepository);

  @Binds
  abstract UserRepository provideUserRepository(UserRepositoryImpl userRepository);

  @Binds
  abstract NetworkUserDataSource bindNetworkUserDataSource(NetworkUserDataSourceImpl networkUserDataSource);

  @Binds
  abstract LocalUserDataSource bindLocalUserDataSource(LocalUserDataSourceImpl localUserDataSource);
}
