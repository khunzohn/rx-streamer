package com.khunzohn.domain.usecase;

import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;

import io.reactivex.ObservableTransformer;

/**
 * Created by khunzohn on 12/17/17.
 */

public abstract class UseCase<Upstream, Downstream> implements ObservableTransformer<Upstream, Downstream> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    public UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    PostExecutionThread getPostExecutionThread() {
        return postExecutionThread;
    }

    ThreadExecutor getThreadExecutor() {
        return threadExecutor;
    }
}
