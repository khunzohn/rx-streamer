package com.khunzohn.domain.usecase;

import com.khunzohn.domain.executor.PostExecutionThread;
import com.khunzohn.domain.executor.ThreadExecutor;
import com.khunzohn.domain.model.User;
import com.khunzohn.domain.repository.UserRepository;
import io.reactivex.Completable;
import javax.inject.Inject;
/**
 * Created by KHUNZOHN on 12/1/18.
 */

public class AddUserUseCase extends CompletableUseCase<AddUserUseCase.Action> {

  private final UserRepository userRepository;

  @Inject
  AddUserUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,UserRepository userRepository) {
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
