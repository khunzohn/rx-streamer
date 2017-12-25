package com.khunzohn.rxstreamer.feature.main.user;

import com.khunzohn.domain.model.Users;
import com.khunzohn.domain.usecase.GetUsersUseCase;
import com.khunzohn.rxstreamer.feature.BaseViewModel;
import com.khunzohn.rxstreamer.mapper.UsersModelMapper;
import com.khunzohn.rxstreamer.model.UsersModel;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import javax.inject.Inject;

/**
 * Created by khunzohn on 12/23/17.
 */

public class UsersViewModel extends BaseViewModel {

  private final GetUsersUseCase getUsersUseCase;
  private final UsersModelMapper usersModelMapper;

  private final BehaviorSubject<UsersModel> userSubject;

  @Inject
  public UsersViewModel(GetUsersUseCase getUsersUseCase, UsersModelMapper usersModelMapper) {
    this.getUsersUseCase = getUsersUseCase;
    this.usersModelMapper = usersModelMapper;
    this.userSubject = BehaviorSubject.create();
  }

  void getUsers() {
    add(Observable.just(new GetUsersUseCase.Action())
        .compose(getUsersUseCase)
        .map(usersModelMapper::map)
        .subscribe(userSubject::onNext,
            e -> userSubject.onNext(usersModelMapper.map(Users.error(e)))));
  }

  Observable<UsersModel> getUsersStream() {
    return userSubject;
  }
}
