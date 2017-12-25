package com.khunzohn.domain.model;

import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khunzohn on 12/23/17.
 */

@AutoValue
public abstract class Users extends DomainModel {

  public static Users success(List<User> users) {
    return builder()
        .state(State.SUCCESS)
        .users(users)
        .build();
  }

  public static Users error(Throwable error) {
    return builder()
        .error(error)
        .state(State.ERROR)
        .users(new ArrayList<>())
        .build();
  }

  public static Users progress() {
    return builder()
        .state(State.PROGRESS)
        .users(new ArrayList<>())
        .build();
  }

  public abstract List<User> users();

  public static Users create(List<User> users, State state, Throwable error) {
    return builder()
        .error(error)
        .state(state)
        .users(users)
        .build();
  }

  public static Builder builder() {
    return new AutoValue_Users.Builder();
  }

  @AutoValue.Builder public abstract static class Builder {
    public abstract Builder users(List<User> users);

    public abstract Builder error(Throwable error);

    public abstract Builder state(State state);

    public abstract Users build();
  }
}
