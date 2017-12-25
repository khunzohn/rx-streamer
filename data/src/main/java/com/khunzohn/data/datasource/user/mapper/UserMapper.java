package com.khunzohn.data.datasource.user.mapper;

import com.khunzohn.data.datasource.DomainMapper;
import com.khunzohn.data.entity.UserEntity;
import com.khunzohn.domain.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by khunzohn on 12/23/17.
 */

public class UserMapper extends DomainMapper<User, UserEntity> {

  @Inject
  public UserMapper() {}

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

  public List<User> map(List<UserEntity> entities) {
    List<User> domainModels = new ArrayList<>();
    for (UserEntity entity:entities) {
      domainModels.add(map(entity));
    }
    return domainModels;
  }
}
