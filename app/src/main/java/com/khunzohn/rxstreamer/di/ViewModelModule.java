package com.khunzohn.rxstreamer.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.khunzohn.rxstreamer.feature.main.user.UsersViewModel;
import com.khunzohn.rxstreamer.feature.news.list.NewListViewModel;
import com.khunzohn.rxstreamer.viewmodel.ModelProviderFactory;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by khunzohn on 12/18/17.
 */

@Module
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(UsersViewModel.class)
  abstract ViewModel bindUsersViewModel(UsersViewModel usersViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(NewListViewModel.class)
  abstract ViewModel bindNewListViewModel(NewListViewModel newListViewModel);

  @Binds
  abstract ViewModelProvider.Factory bindProviderFactory(ModelProviderFactory providerFactory);
}
