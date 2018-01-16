package com.khunzohn.data.datasource.user.mapper;

import com.khunzohn.data.datasource.Mapper;
import com.khunzohn.data.model.entity.UserEntity;
import com.khunzohn.data.model.response.UserResponse;
import com.khunzohn.domain.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by codigo on 8/1/18.
 */

public class UserResponseMapper extends Mapper<User, UserResponse> {
  @Inject public UserResponseMapper() {

  }

  public List<User> map(List<UserResponse> responses) {
    List<User> users = new ArrayList<>();
    for (UserResponse response : responses) {
      users.add(map(response));
    }
    return users;
  }

  @Override public User map(UserResponse response) {
    return User.success()
        .gistsUrl(response.gistsUrl)
        .reposUrl(response.reposUrl)
        .followingUrl(response.followingUrl)
        .starredUrl(response.starredUrl)
        .login(response.login)
        .followersUrl(response.followersUrl)
        .type(response.type)
        .url(response.url)
        .subscriptionsUrl(response.subscriptionsUrl)
        .receivedEventsUrl(response.receivedEventsUrl)
        .avatarUrl(response.avatarUrl)
        .eventsUrl(response.eventsUrl)
        .htmlUrl(response.htmlUrl)
        .siteAdmin(response.siteAdmin)
        .id(response.id)
        .gravatarId(response.gravatarId)
        .organizationsUrl(response.organizationsUrl)
        .build();
  }

  public UserEntity mapToEntity(UserResponse userResponse) {
    UserEntity entity = new UserEntity();
    entity.gistsUrl = userResponse.gistsUrl;
    entity.reposUrl = userResponse.reposUrl;
    entity.followingUrl = userResponse.followingUrl;
    entity.starredUrl = userResponse.starredUrl;
    entity.login = userResponse.login;
    entity.followersUrl = userResponse.followersUrl;
    entity.type = userResponse.type;
    entity.url = userResponse.url;
    entity.subscriptionsUrl = userResponse.subscriptionsUrl;
    entity.receivedEventsUrl = userResponse.receivedEventsUrl;
    entity.avatarUrl = userResponse.avatarUrl;
    entity.eventsUrl = userResponse.eventsUrl;
    entity.htmlUrl = userResponse.htmlUrl;
    entity.siteAdmin = userResponse.siteAdmin;
    entity.id = userResponse.id;
    entity.gravatarId = userResponse.gravatarId;
    entity.organizationsUrl = userResponse.organizationsUrl;
    return entity;
  }

  public List<UserEntity> mapToEntities(List<UserResponse> userResponses) {
    List<UserEntity> entities = new ArrayList<>();
    for (UserResponse response:userResponses) {
      entities.add(mapToEntity(response));
    }
    return entities;
  }
}
