package com.khunzohn.data.datasource.user.mapper;

import com.khunzohn.data.datasource.Mapper;
import com.khunzohn.data.model.entity.UserEntity;
import com.khunzohn.domain.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by codigo on 8/1/18.
 */

public class UserEntityMapper extends Mapper<User, UserEntity> {

  @Inject public UserEntityMapper() {

  }

  public List<User> map(List<UserEntity> entities) {
    List<User> users = null;
    if (entities != null) {
      users = new ArrayList<>();
      for (UserEntity entity : entities) {
        users.add(map(entity));
      }
    }
    return users;
  }

  @Override public User map(UserEntity entity) {
    return User.success()
        .gistsUrl(entity.gistsUrl)
        .reposUrl(entity.reposUrl)
        .followingUrl(entity.followingUrl)
        .starredUrl(entity.starredUrl)
        .login(entity.login)
        .followersUrl(entity.followersUrl)
        .type(entity.type)
        .url(entity.url)
        .subscriptionsUrl(entity.subscriptionsUrl)
        .receivedEventsUrl(entity.receivedEventsUrl)
        .avatarUrl(entity.avatarUrl)
        .eventsUrl(entity.eventsUrl)
        .htmlUrl(entity.htmlUrl)
        .siteAdmin(entity.siteAdmin)
        .id(entity.id)
        .gravatarId(entity.gravatarId)
        .organizationsUrl(entity.organizationsUrl)
        .build();
  }

  public UserEntity reverse(User user) {
    UserEntity userEntity = new UserEntity();
    userEntity.gistsUrl = user.gistsUrl();
    userEntity.reposUrl = user.reposUrl();
    userEntity.followingUrl = user.followingUrl();
    userEntity.starredUrl = user.starredUrl();
    userEntity.login = user.login();
    userEntity.followersUrl = user.followersUrl();
    userEntity.type = user.type();
    userEntity.url = user.url();
    userEntity.subscriptionsUrl = user.subscriptionsUrl();
    userEntity.receivedEventsUrl = user.receivedEventsUrl();
    userEntity.avatarUrl = user.avatarUrl();
    userEntity.eventsUrl = user.eventsUrl();
    userEntity.htmlUrl = user.htmlUrl();
    userEntity.siteAdmin = user.siteAdmin();
    userEntity.id = user.id();
    userEntity.gravatarId = user.gravatarId();
    userEntity.organizationsUrl = user.organizationsUrl();
    return userEntity;
  }
}
