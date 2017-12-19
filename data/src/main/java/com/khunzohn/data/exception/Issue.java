package com.khunzohn.data.exception;

/**
 * Created by khunzohn on 12/18/17.
 */

public enum Issue {
    NETWORK(true), SERVER(false), API(false);

    private boolean shouldRetry;

    Issue(boolean shouldRetry) {
        this.shouldRetry = shouldRetry;
    }

    public boolean shouldRetry() {
        return shouldRetry;
    }
}
