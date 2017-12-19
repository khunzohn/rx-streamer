package com.khunzohn.domain.executor;

import io.reactivex.Scheduler;

/**
 * Created by khunzohn on 12/17/17.
 */

public interface PostExecutionThread {
    Scheduler getScheduler();
}
