package com.khunzohn.rxstreamer.di;

import com.khunzohn.data.datasource.user.local.LocalUserDataSource;
import com.khunzohn.data.datasource.user.local.LocalUserDataSourceImpl;
import com.khunzohn.data.datasource.user.network.NetworkUserDataSource;
import com.khunzohn.data.datasource.user.network.NetworkUserDataSourceImpl;
import com.khunzohn.data.repository.NewRepositoryImpl;
import com.khunzohn.data.repository.StudentRepositoryImpl;
import com.khunzohn.data.repository.UserRepositoryImpl;
import com.khunzohn.domain.repository.NewRepository;
import com.khunzohn.domain.repository.StudentRepository;
import com.khunzohn.domain.repository.UserRepository;
import dagger.Binds;
import dagger.Module;

/**
 * Created by khunzohn on 12/25/17.
 */

@Module
abstract class RepositoryModule {

  @Binds abstract NewRepository bindNewRepository(NewRepositoryImpl newRepository);

  @Binds abstract UserRepository bindUserRepository(UserRepositoryImpl userRepository);

  @Binds abstract StudentRepository bindStudentRepository(StudentRepositoryImpl studentRepository);

  @Binds
  abstract NetworkUserDataSource bindNetworkUserDataSource(NetworkUserDataSourceImpl networkUserDataSource);

  @Binds
  abstract LocalUserDataSource bindLocalUserDataSource(LocalUserDataSourceImpl localUserDataSource);
}
