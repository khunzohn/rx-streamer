package com.khunzohn.data.model.response;

import com.google.gson.annotations.SerializedName;

public final class UserResponse {

  @SerializedName("gists_url") public final String gistsUrl;

  @SerializedName("repos_url") public final String reposUrl;

  @SerializedName("following_url") public final String followingUrl;

  @SerializedName("starred_url") public final String starredUrl;

  @SerializedName("login") public final String login;

  @SerializedName("followers_url") public final String followersUrl;

  @SerializedName("type") public final String type;

  @SerializedName("url") public final String url;

  @SerializedName("subscriptions_url") public final String subscriptionsUrl;

  @SerializedName("received_events_url") public final String receivedEventsUrl;

  @SerializedName("avatar_url") public final String avatarUrl;

  @SerializedName("events_url") public final String eventsUrl;

  @SerializedName("html_url") public final String htmlUrl;

  @SerializedName("site_admin") public final boolean siteAdmin;

  @SerializedName("id") public final int id;

  @SerializedName("gravatar_id") public final String gravatarId;

  @SerializedName("organizations_url") public final String organizationsUrl;

  public UserResponse(String gistsUrl, String reposUrl, String followingUrl, String starredUrl,
      String login, String followersUrl, String type, String url, String subscriptionsUrl,
      String receivedEventsUrl, String avatarUrl, String eventsUrl, String htmlUrl,
      boolean siteAdmin, int id, String gravatarId, String organizationsUrl) {
    this.gistsUrl = gistsUrl;
    this.reposUrl = reposUrl;
    this.followingUrl = followingUrl;
    this.starredUrl = starredUrl;
    this.login = login;
    this.followersUrl = followersUrl;
    this.type = type;
    this.url = url;
    this.subscriptionsUrl = subscriptionsUrl;
    this.receivedEventsUrl = receivedEventsUrl;
    this.avatarUrl = avatarUrl;
    this.eventsUrl = eventsUrl;
    this.htmlUrl = htmlUrl;
    this.siteAdmin = siteAdmin;
    this.id = id;
    this.gravatarId = gravatarId;
    this.organizationsUrl = organizationsUrl;
  }

  @Override public String toString() {
    return "UserResponse{"
        + "gists_url = '"
        + gistsUrl
        + '\''
        + ",repos_url = '"
        + reposUrl
        + '\''
        + ",following_url = '"
        + followingUrl
        + '\''
        + ",starred_url = '"
        + starredUrl
        + '\''
        + ",login = '"
        + login
        + '\''
        + ",followers_url = '"
        + followersUrl
        + '\''
        + ",type = '"
        + type
        + '\''
        + ",url = '"
        + url
        + '\''
        + ",subscriptions_url = '"
        + subscriptionsUrl
        + '\''
        + ",received_events_url = '"
        + receivedEventsUrl
        + '\''
        + ",avatar_url = '"
        + avatarUrl
        + '\''
        + ",events_url = '"
        + eventsUrl
        + '\''
        + ",html_url = '"
        + htmlUrl
        + '\''
        + ",site_admin = '"
        + siteAdmin
        + '\''
        + ",id = '"
        + id
        + '\''
        + ",gravatar_id = '"
        + gravatarId
        + '\''
        + ",organizations_url = '"
        + organizationsUrl
        + '\''
        + "}";
  }
}