package com.khunzohn.domain.usecase;

import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;
import com.khunzohn.domain.model.Users;
import com.khunzohn.domain.repository.UserRepository;
import com.khunzohn.domain.usecase.base.UseCase;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * Created by khunzohn on 12/23/17.
 */

public class GetUsers extends UseCase<GetUsers.Action, Users> {

  private final UserRepository userRepository;

  @Inject
  public GetUsers(ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread, UserRepository userRepository) {
    super(threadExecutor, postExecutionThread);
    this.userRepository = userRepository;
  }

  @Override public Observable<Users> execute(Action ignored) {
    return userRepository.streamUsers().toObservable();
  }

  @Override public Users progress() {
    return Users.progress(null);
  }

  @Override public Users error(Throwable throwable) {
    return Users.error(throwable,null);
  }

  public static class Action {

  }
}
