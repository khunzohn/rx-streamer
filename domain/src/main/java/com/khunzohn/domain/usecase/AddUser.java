package com.khunzohn.domain.usecase;

import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;
import com.khunzohn.domain.model.User;
import com.khunzohn.domain.repository.UserRepository;
import com.khunzohn.domain.usecase.base.CompletableUseCase;
import io.reactivex.Completable;
import javax.inject.Inject;

/**
 * Created by khunzohn on 12/1/18.
 */

public class AddUser extends CompletableUseCase<AddUser.Action> {

  private final UserRepository userRepository;

  @Inject AddUser(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,UserRepository userRepository) {
    super(threadExecutor, postExecutionThread);
    this.userRepository = userRepository;
  }

  @Override protected Completable executeInternal(Action action) {
    return userRepository.addUser(action.user);
  }

  public static class Action {
    private final User user;

    public Action(User user) {
      this.user = user;
    }
  }
}
