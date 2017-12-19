package com.khunzohn.rxstreamer.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;
import com.khunzohn.domain.repository.NewRepository;
import com.khunzohn.domain.usecase.GetNewsUseCase;
import com.khunzohn.rxstreamer.feature.news.list.NewListViewModel;
import com.khunzohn.rxstreamer.viewmodel.ModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

/**
 * Created by khunzohn on 12/18/17.
 */

@Module
abstract class ViewModelModule {

    @Provides
    static GetNewsUseCase provideGetNewsUseCase(NewRepository newRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetNewsUseCase(newRepository, threadExecutor, postExecutionThread);
    }

    @Binds
    @IntoMap
    @ViewModelKey(NewListViewModel.class)
    abstract ViewModel bindNewListViewModel(NewListViewModel newListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindProviderFactory(ModelProviderFactory providerFactory);
}
