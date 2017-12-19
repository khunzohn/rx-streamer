package com.khunzohn.domain.model;

import io.reactivex.annotations.Nullable;

/**
 * Created by khunzohn on 12/18/17.
 */

public abstract class DomainModel {
    @Nullable
    public final Throwable error;
    public final State state;

    public DomainModel(Throwable error, State state) {
        this.error = error;
        this.state = state;
    }

    public enum State {
        PROGRESS, ERROR, SUCCESS
    }
}
