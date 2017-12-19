package com.khunzohn.domain.model;

import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by khunzohn on 12/17/17.
 */

public final class News extends DomainModel {
    @Nullable
    public final List<New> news;

    public News(State state, List<New> news, Throwable error) {
        super(error, state);
        this.news = news;
    }

    public static News
    success(List<New> news) {
        return new News(State.SUCCESS, news, null);
    }

    public static News error(Throwable error) {
        return new News(State.ERROR, null, error);
    }

    public static News progress() {
        return new News(State.PROGRESS, null, null);
    }
}
