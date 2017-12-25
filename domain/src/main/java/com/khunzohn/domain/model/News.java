package com.khunzohn.domain.model;

import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khunzohn on 12/17/17.
 */

@AutoValue
public abstract class News extends DomainModel {
  public static News success(List<New> news) {
    return builder()
        .state(State.SUCCESS)
        .news(news)
        .build();
  }

  public static News error(Throwable error) {
    return builder()
        .error(error)
        .news(new ArrayList<>())
        .state(State.ERROR)
        .build();
  }

  public static News progress() {
    return builder()
        .state(State.PROGRESS)
        .news(new ArrayList<>())
        .build();
  }

  public abstract List<New> news();

  public static News create(List<New> news, Throwable error, State state) {
    return builder()
        .error(error)
        .state(state)
        .news(news)
        .build();
  }

  public static Builder builder() {
    return new AutoValue_News.Builder();
  }

  @AutoValue.Builder public abstract static class Builder {
    public abstract Builder news(List<New> news);

    public abstract Builder state(State state);

    public abstract Builder error(Throwable error);

    public abstract News build();
  }
}
