package com.khunzohn.rxstreamer.mapper;

import android.content.Context;
import android.view.View;
import com.khunzohn.data.exception.DataException;
import com.khunzohn.domain.model.Users;
import com.khunzohn.rxstreamer.model.UserModel;
import com.khunzohn.rxstreamer.model.UsersModel;
import com.khunzohn.rxstreamer.util.IssueFactory;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by khunzohn on 12/23/17.
 */

public class UsersModelMapper extends ModelMapper<UsersModel, Users> {

  private final UserModelMapper userModelMapper;
  private final IssueFactory issueFactory;

  @Inject
  public UsersModelMapper(Context context, UserModelMapper userModelMapper,
      IssueFactory issueFactory) {
    super(context);
    this.issueFactory = issueFactory;
    this.userModelMapper = userModelMapper;
  }

  @Override public UsersModel map(Users domainModel) {
    int progressVisibility = View.GONE;
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
        break;
      case ERROR:
        errorVisibility = View.VISIBLE;
        if (domainModel.error() instanceof DataException) {
          DataException de = (DataException) domainModel.error();
          if (de.shouldRetry()) {
            retryVisibility = View.VISIBLE;
          }
          errorMessage = issueFactory.getMessage(de.getIssue());
        } else {
          errorMessage = domainModel.error().getLocalizedMessage();
        }
        break;
      default:
        progressVisibility = View.VISIBLE;
    }

    return UsersModel.builder()
        .progressVisibility(progressVisibility)
        .errorVisibility(errorVisibility)
        .retryVisibility(retryVisibility)
        .userListVisibility(userListVisibility)
        .errorMessage(errorMessage)
        .userModels(userModels)
        .build();
  }
}
