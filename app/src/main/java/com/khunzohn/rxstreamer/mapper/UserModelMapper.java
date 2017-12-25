package com.khunzohn.rxstreamer.mapper;

import android.content.Context;
import com.khunzohn.domain.model.User;
import com.khunzohn.rxstreamer.model.UserModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by khunzohn on 12/23/17.
 */

public class UserModelMapper extends ModelMapper<UserModel, User> {

  @Inject
  public UserModelMapper(Context context) {
    super(context);
  }

  @Override public UserModel map(User domainModel) {
    return UserModel.builder()
        .gistsUrl(domainModel.gistsUrl())
        .reposUrl(domainModel.reposUrl())
        .followingUrl(domainModel.followingUrl())
        .starredUrl(domainModel.starredUrl())
        .login(domainModel.login())
        .followersUrl(domainModel.followersUrl())
        .type(domainModel.type())
        .url(domainModel.url())
        .subscriptionsUrl(domainModel.subscriptionsUrl())
        .receivedEventsUrl(domainModel.receivedEventsUrl())
        .avatarUrl(domainModel.avatarUrl())
        .eventsUrl(domainModel.eventsUrl())
        .htmlUrl(domainModel.htmlUrl())
        .siteAdmin(domainModel.siteAdmin())
        .id(domainModel.id())
        .gravatarId(domainModel.gravatarId())
        .organizationsUrl(domainModel.organizationsUrl())
        .build();
  }

  List<UserModel> map(List<User> domainModels) {
    List<UserModel> viewModels = new ArrayList<>();
    for (User domainModel : domainModels) {
      viewModels.add(map(domainModel));
    }
    return viewModels;
  }
}
