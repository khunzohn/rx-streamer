package com.khunzohn.rxstreamer.di;

import com.khunzohn.rxstreamer.feature.main.user.UsersFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by khunzohn on 12/25/17.
 */

@Module
abstract class MainFragmentBuilder {

  @ContributesAndroidInjector
  abstract UsersFragment contributeUsersFragment();
}

