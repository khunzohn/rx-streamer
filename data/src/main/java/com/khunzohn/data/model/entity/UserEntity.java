package com.khunzohn.data.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by codigo on 5/1/18.
 */

@Entity(tableName = "users")
public class UserEntity {

  @PrimaryKey public int id;

  @ColumnInfo(name = "gists_url") public String gistsUrl;

  @ColumnInfo(name = "repos_url") public String reposUrl;

  @ColumnInfo(name = "following_url") public String followingUrl;

  @ColumnInfo(name = "starred_url") public String starredUrl;

  @ColumnInfo(name = "login") public String login;

  @ColumnInfo(name = "followers_url") public String followersUrl;

  @ColumnInfo(name = "type") public String type;

  @ColumnInfo(name = "url") public String url;

  @ColumnInfo(name = "subscriptions_url") public String subscriptionsUrl;

  @ColumnInfo(name = "received_events_url") public String receivedEventsUrl;

  @ColumnInfo(name = "avatar_url") public String avatarUrl;

  @ColumnInfo(name = "events_url") public String eventsUrl;

  @ColumnInfo(name = "html_url") public String htmlUrl;

  @ColumnInfo(name = "site_admin") public boolean siteAdmin;

  @ColumnInfo(name = "gravatar_id") public String gravatarId;

  @ColumnInfo(name = "organizations_url") public String organizationsUrl;
}
