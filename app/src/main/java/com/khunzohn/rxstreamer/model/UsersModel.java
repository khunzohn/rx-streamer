package com.khunzohn.rxstreamer.model;

import com.google.auto.value.AutoValue;
import com.khunzohn.rxstreamer.util.Visibility;
import io.reactivex.annotations.Nullable;
import java.util.List;

/**
 * Created by khunzohn on 12/23/17.
 */

@AutoValue
public abstract class UsersModel {
  @Visibility public abstract int progressVisibility();

  @Visibility public abstract int errorVisibility();

  @Visibility public abstract int retryVisibility();

  @Visibility public abstract int userListVisibility();

  public abstract String errorMessage();

  public abstract List<UserModel> userModels();

  public static UsersModel create(int progressVisibility, int errorVisibility, int retryVisibility,
      int userListVisibility, String errorMessage, List<UserModel> userModels) {
    return builder()
        .progressVisibility(progressVisibility)
        .errorVisibility(errorVisibility)
        .retryVisibility(retryVisibility)
        .userListVisibility(userListVisibility)
        .errorMessage(errorMessage)
        .userModels(userModels)
        .build();
  }

  public static Builder builder() {
    return new AutoValue_UsersModel.Builder();
  }

  @AutoValue.Builder public abstract static class Builder {
    public abstract Builder progressVisibility(int progressVisibility);

    public abstract Builder errorVisibility(int errorVisibility);

    public abstract Builder retryVisibility(int retryVisibility);

    public abstract Builder userListVisibility(int userListVisibility);

    public abstract Builder errorMessage(String errorMessage);

    public abstract Builder userModels(List<UserModel> userModels);

    public abstract UsersModel build();
  }
}
