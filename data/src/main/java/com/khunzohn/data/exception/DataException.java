package com.khunzohn.data.exception;

/**
 * Created by khunzohn on 12/18/17.
 */

public class DataException extends Exception {
    private Issue issue;

    public DataException(Issue issue) {
        this.issue = issue;
    }

    public Issue getIssue() {
        return issue;
    }

    public boolean is(Issue issue) {
        return this.issue == issue;
    }

    public boolean shouldRetry() {
        return issue.shouldRetry();
    }
}
