package com.khunzohn.domain.model;

/**
 * Created by khunzohn on 12/17/17.
 */

public final class New extends DomainModel {
    public final long id;
    public final String title;
    public final String content;
    public final String date;
    public final String reporter;

    public New(long id, String title, String content, String date, String reporter, State state, Throwable error) {
        super(error, state);
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.reporter = reporter;
    }

    public static New success(long id,String title,String content,String date,String reporter) {
        return new New(id,title,content,date,reporter,State.SUCCESS,null);
    }

    public static New progress() {
        return new New(-1,"","","","",State.SUCCESS,null);
    }

    public static New error(Throwable error) {
        return new New(-1,"","","","",State.ERROR,error);
    }
}
