package com.khunzohn.rxstreamer.feature.main.user;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.jakewharton.rxbinding2.view.RxView;
import com.khunzohn.rxstreamer.R;
import com.khunzohn.rxstreamer.feature.BaseFragment;
import com.khunzohn.rxstreamer.model.UserModel;
import com.khunzohn.rxstreamer.model.UsersModel;
import dagger.android.support.AndroidSupportInjection;
import javax.inject.Inject;

/**
 * Created by khunzohn on 12/23/17.
 */

public class UsersFragment extends BaseFragment {

  @BindView(R.id.tv_error) TextView tvError;
  @BindView(R.id.btn_retry) Button btnRetry;
  @BindView(R.id.layout_error) RelativeLayout layoutError;
  @BindView(R.id.progress_bar) ProgressBar progressBar;
  @BindView(R.id.rv_users) RecyclerView rvUsers;

  @Inject ViewModelProvider.Factory factory;

  private UsersViewModel usersViewModel;

  private UserAdapter userAdapter;

  public UsersFragment() {

  }

  @Override public void onAttach(Context context) {
    AndroidSupportInjection.inject(this);
    super.onAttach(context);
  }

  public static UsersFragment getInstance() {
    return new UsersFragment();
  }

  @Override public int getLayoutResource() {
    return R.layout.fragment_users;
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    userAdapter = new UserAdapter();
    rvUsers.setAdapter(userAdapter);

    LinearLayoutManager layoutManager =
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    rvUsers.setLayoutManager(layoutManager);

    usersViewModel = ViewModelProviders.of(this, factory).get(UsersViewModel.class);
    if (savedInstanceState == null) {
      usersViewModel.getUsers();
    }
    startSubscriptions();
  }

  private void startSubscriptions() {
    add(usersViewModel.getUsersStream().subscribe(this::render));
    add(userAdapter.itemClickStream().subscribe(this::goToUserDetail));
    add(RxView.clicks(btnRetry).subscribe(ignored -> usersViewModel.getUsers()));
  }

  private void render(UsersModel usersModel) {
    layoutError.setVisibility(usersModel.errorVisibility());
    btnRetry.setVisibility(usersModel.retryVisibility());
    progressBar.setVisibility(usersModel.progressVisibility());
    rvUsers.setVisibility(usersModel.userListVisibility());
    tvError.setText(usersModel.errorMessage());
    userAdapter.setModels(usersModel.userModels());
  }

  private void goToUserDetail(UserModel userModel) {
    showSnack("To detail of " + userModel.login(), R.string.btn_label_dismiss, v -> {
    }, Snackbar.LENGTH_SHORT);
  }
}
