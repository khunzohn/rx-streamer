package com.khunzohn.domain.model;

import com.google.auto.value.AutoValue;

/**
 * Created by khunzohn on 12/17/17.
 */

@AutoValue
public abstract class New extends DomainModel {

  public static Builder success() {
    return builder()
        .error(null)
        .state(State.SUCCESS);
  }

  public static New progress() {
    return builder()
        .id(1L)
        .title("")
        .content("")
        .date("")
        .reporter("")
        .error(null)
        .state(State.PROGRESS)
        .build();
  }

  public static New error(Throwable error) {
    return builder()
        .id(1L)
        .title("")
        .content("")
        .date("")
        .reporter("")
        .error(error)
        .state(State.ERROR)
        .build();
  }
  public abstract Long id();

  public abstract String title();

  public abstract String content();

  public abstract String date();

  public abstract String reporter();

  public static New create(Long id, String title, String content, String date, String reporter,
      Throwable error, State state) {
    return builder()
        .id(id)
        .title(title)
        .content(content)
        .date(date)
        .reporter(reporter)
        .error(error)
        .state(state)
        .build();
  }

  public static Builder builder() {
    return new AutoValue_New.Builder();
  }

  @AutoValue.Builder public abstract static class Builder {
    public abstract Builder id(Long id);

    public abstract Builder title(String title);

    public abstract Builder content(String content);

    public abstract Builder date(String date);

    public abstract Builder reporter(String reporter);

    public abstract Builder error(Throwable error);

    public abstract Builder state(State state);

    public abstract New build();
  }
}
