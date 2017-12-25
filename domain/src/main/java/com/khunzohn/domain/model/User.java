package com.khunzohn.domain.model;

import com.google.auto.value.AutoValue;

/**
 * Created by khunzohn on 12/23/17.
 */
@AutoValue
public abstract class User extends DomainModel {

  public abstract String gistsUrl();

  public abstract String reposUrl();

  public abstract String followingUrl();

  public abstract String starredUrl();

  public abstract String login();

  public abstract String followersUrl();

  public abstract String type();

  public abstract String url();

  public abstract String subscriptionsUrl();

  public abstract String receivedEventsUrl();

  public abstract String avatarUrl();

  public abstract String eventsUrl();

  public abstract String htmlUrl();

  public abstract boolean siteAdmin();

  public abstract int id();

  public abstract String gravatarId();

  public abstract String organizationsUrl();

  public static User create(String gistsUrl, String reposUrl, String followingUrl,
      String starredUrl, String login, String followersUrl, String type, String url,
      String subscriptionsUrl, String receivedEventsUrl, String avatarUrl, String eventsUrl,
      String htmlUrl, boolean siteAdmin, int id, String gravatarId,
      String organizationsUrl, Throwable error, State state) {
    return builder()
        .error(error)
        .state(state)
        .gistsUrl(gistsUrl)
        .reposUrl(reposUrl)
        .followingUrl(followingUrl)
        .starredUrl(starredUrl)
        .login(login)
        .followersUrl(followersUrl)
        .type(type)
        .url(url)
        .subscriptionsUrl(subscriptionsUrl)
        .receivedEventsUrl(receivedEventsUrl)
        .avatarUrl(avatarUrl)
        .eventsUrl(eventsUrl)
        .htmlUrl(htmlUrl)
        .siteAdmin(siteAdmin)
        .id(id)
        .gravatarId(gravatarId)
        .organizationsUrl(organizationsUrl)
        .build();
  }

  public static Builder builder() {
    return new AutoValue_User.Builder();
  }

  public static Builder success() {
    return builder().state(State.SUCCESS);
  }

  @AutoValue.Builder public abstract static class Builder {
    public abstract Builder gistsUrl(String gistsUrl);

    public abstract Builder reposUrl(String reposUrl);

    public abstract Builder followingUrl(String followingUrl);

    public abstract Builder starredUrl(String starredUrl);

    public abstract Builder login(String login);

    public abstract Builder followersUrl(String followersUrl);

    public abstract Builder type(String type);

    public abstract Builder url(String url);

    public abstract Builder subscriptionsUrl(String subscriptionsUrl);

    public abstract Builder receivedEventsUrl(String receivedEventsUrl);

    public abstract Builder avatarUrl(String avatarUrl);

    public abstract Builder eventsUrl(String eventsUrl);

    public abstract Builder htmlUrl(String htmlUrl);

    public abstract Builder siteAdmin(boolean siteAdmin);

    public abstract Builder id(int id);

    public abstract Builder gravatarId(String gravatarId);

    public abstract Builder organizationsUrl(String organizationsUrl);

    public abstract Builder error(Throwable error);

    public abstract Builder state(State state);

    public abstract User build();
  }
}
