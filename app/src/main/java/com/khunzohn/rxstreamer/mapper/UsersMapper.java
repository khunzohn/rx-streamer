package com.khunzohn.rxstreamer.mapper;

import android.content.Context;
import android.view.View;
import com.khunzohn.data.exception.DataException;
import com.khunzohn.domain.model.Users;
import com.khunzohn.rxstreamer.R;
import com.khunzohn.rxstreamer.model.UserModel;
import com.khunzohn.rxstreamer.model.UsersModel;
import com.khunzohn.rxstreamer.util.IssueFactory;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by khunzohn on 12/23/17.
 */

public class UsersMapper extends ViewStateMapper<UsersModel, Users> {

  private final UserMapper userModelMapper;
  private final IssueFactory issueFactory;

  @Inject
  public UsersMapper(Context context, UserMapper userModelMapper,
      IssueFactory issueFactory) {
    super(context);
    this.issueFactory = issueFactory;
    this.userModelMapper = userModelMapper;
  }

  @Override public UsersModel map(Users domainModel) {
    boolean errorIncludeData = false;
    boolean shouldRetry = false;
    int progressVisibility = View.GONE;
    String errorActionMessage = "";
    int errorVisibility = View.GONE;
    int retryVisibility = View.INVISIBLE;
    int userListVisibility = View.GONE;
    String errorMessage = "";
    List<UserModel> userModels = new ArrayList<>();
    switch (domainModel.state()) {
      case SUCCESS:
        userListVisibility = View.VISIBLE;
        userModels = userModelMapper.map(domainModel.users());
        break;
      case PROGRESS:
        progressVisibility = View.VISIBLE;
        if (domainModel.users() != null && domainModel.users().size() > 0) {
          userModels = userModelMapper.map(domainModel.users());
          userListVisibility = View.VISIBLE;
        }
        break;
      case ERROR:
        errorVisibility = View.VISIBLE;
        errorActionMessage = getContext().getString(R.string.error_action_message_ok);

        if (domainModel.error() instanceof DataException) {
          DataException de = (DataException) domainModel.error();
          if (de.shouldRetry()) {
            errorActionMessage = getContext().getString(R.string.error_action_message_retry);
            retryVisibility = View.VISIBLE;
            shouldRetry = true;
          }
          errorMessage = issueFactory.getMessage(de.getIssue());
        } else {
          errorMessage = domainModel.error().getLocalizedMessage();
        }

        if (domainModel.users() != null && domainModel.users().size() > 0) {
          userModels = userModelMapper.map(domainModel.users());
          userListVisibility = View.VISIBLE;
          errorVisibility = View.GONE;
          retryVisibility = View.INVISIBLE;
          errorIncludeData = true;
        }
        break;
      default:
        progressVisibility = View.VISIBLE;
    }

    return UsersModel.builder()
        .shouldRetry(shouldRetry)
        .errorActionMessage(errorActionMessage)
        .errorIncludeData(errorIncludeData)
        .progressVisibility(progressVisibility)
        .errorVisibility(errorVisibility)
        .retryVisibility(retryVisibility)
        .userListVisibility(userListVisibility)
        .errorMessage(errorMessage)
        .userModels(userModels)
        .build();
  }
}
