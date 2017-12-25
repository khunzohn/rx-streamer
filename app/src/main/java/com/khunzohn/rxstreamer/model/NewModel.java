package com.khunzohn.rxstreamer.model;

import com.google.auto.value.AutoValue;

/**
 * Created by khunzohn on 12/18/17.
 */

@AutoValue
public abstract class NewModel {
  public static NewModel create(long id, String title, String content, String date,
      String reporter) {
    return builder()
        .id(id)
        .title(title)
        .content(content)
        .date(date)
        .reporter(reporter)
        .build();
  }

  public static Builder builder() {
    return new AutoValue_NewModel.Builder();
  }

  public abstract long id();

  public abstract String title();

  public abstract String content();

  public abstract String date();

  public abstract String reporter();

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder id(long id);

    public abstract Builder title(String title);

    public abstract Builder content(String content);

    public abstract Builder date(String date);

    public abstract Builder reporter(String reporter);

    public abstract NewModel build();
  }
}
