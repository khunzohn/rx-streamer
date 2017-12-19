package com.khunzohn.rxstreamer.di;

import com.khunzohn.rxstreamer.feature.news.list.NewListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by khunzohn on 12/19/17.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract NewListActivity contributeNewListActivity();
}
