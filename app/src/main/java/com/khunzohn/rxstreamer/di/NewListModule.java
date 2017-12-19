package com.khunzohn.rxstreamer.di;

import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;
import com.khunzohn.domain.repository.NewRepository;
import com.khunzohn.domain.usecase.GetNewsUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by khunzohn on 12/19/17.
 */

@Module
class NewListModule {

    @Provides
    static GetNewsUseCase provideGetNewsUseCase(NewRepository newRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        return new GetNewsUseCase(newRepository, threadExecutor, postExecutionThread);
    }
}
