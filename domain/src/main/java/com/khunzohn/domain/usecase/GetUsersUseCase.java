package com.khunzohn.domain.usecase;

import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;
import com.khunzohn.domain.model.Users;
import com.khunzohn.domain.repository.UserRepository;
import io.reactivex.Observable;
import javax.inject.Inject;

/**
 * Created by khunzohn on 12/23/17.
 */

public class GetUsersUseCase extends UseCase<GetUsersUseCase.Action, Users> {

  private final UserRepository userRepository;

  @Inject
  public GetUsersUseCase(ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread, UserRepository userRepository) {
    super(threadExecutor, postExecutionThread);
    this.userRepository = userRepository;
  }

  @Override public Observable<Users> execute(Action action) {
    return userRepository.getUsers().map(Users::success);
  }

  @Override public Users progress() {
    return Users.progress();
  }

  @Override public Users error(Throwable throwable) {
    return Users.error(throwable);
  }

  public static class Action {

  }
}
