package com.khunzohn.rxstreamer.feature.main.user;

import android.util.Log;
import com.khunzohn.domain.model.State;
import com.khunzohn.domain.model.User;
import com.khunzohn.domain.model.Users;
import com.khunzohn.domain.usecase.AddUser;
import com.khunzohn.domain.usecase.GetUsers;
import com.khunzohn.rxstreamer.feature.BaseViewModel;
import com.khunzohn.rxstreamer.mapper.UsersMapper;
import com.khunzohn.rxstreamer.model.UsersModel;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import java.util.Random;
import javax.inject.Inject;

import static android.content.ContentValues.TAG;

/**
 * Created by khunzohn on 12/23/17.
 */

public class UsersViewModel extends BaseViewModel {

  private final GetUsers getUsers;
  private final UsersMapper usersModelMapper;

  private final BehaviorSubject<UsersModel> userSubject;
  private final AddUser addUser;

  @Inject
  public UsersViewModel(GetUsers getUsers, UsersMapper usersModelMapper,AddUser addUser) {
    this.getUsers = getUsers;
    this.addUser = addUser;
    this.usersModelMapper = usersModelMapper;
    this.userSubject = BehaviorSubject.create();
  }

  void getUsers() {
    add(Observable.just(new GetUsers.Action())
        .compose(getUsers)
        .map(usersModelMapper::map)
        .subscribe(userSubject::onNext,
            e -> userSubject.onNext(usersModelMapper.map(Users.error(e,null)))));
  }


  public void addUser() {
    add(addUser.execute(new AddUser.Action(User.builder().error(null)
        .state(State.SUCCESS)
        .gistsUrl("gist")
        .reposUrl("repo")
        .followingUrl("folo")
        .starredUrl("stared")
        .login("Khun Zohn")
        .followersUrl("")
        .type("")
        .url("")
        .subscriptionsUrl("")
        .receivedEventsUrl("")
        .avatarUrl("")
        .eventsUrl("")
        .htmlUrl("")
        .siteAdmin(false)
        .id(new Random().nextInt())
        .gravatarId("")
        .organizationsUrl("").build()))
        .subscribe(() -> Log.e(TAG,"user added"), Throwable::printStackTrace));
  }

  Observable<UsersModel> getUsersStream() {
    return userSubject;
  }
}
