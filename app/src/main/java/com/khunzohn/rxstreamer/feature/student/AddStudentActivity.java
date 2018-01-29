package com.khunzohn.rxstreamer.feature.student;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.khunzohn.rxstreamer.R;
import com.khunzohn.rxstreamer.feature.BaseActivity;
import dagger.android.AndroidInjection;
import javax.inject.Inject;

/**
 * Created by codigo on 29/1/18.
 */

public class AddStudentActivity extends BaseActivity {

  @BindView(R.id.edtName) EditText edtName;
  @BindView(R.id.edtAddress) EditText edtAddress;
  @BindView(R.id.edtPhone) EditText edtPhone;
  @BindView(R.id.edtSsn) EditText edtSsn;
  @BindView(R.id.edtEmail) EditText edtEmail;
  @BindView(R.id.btnEnrol) Button btnEnrol;
  @BindView(R.id.progressBar) ProgressBar progressBar;

  private AddStudentViewModel viewModel;

  @Inject ViewModelProvider.Factory viewModelFactory;

  public static void start(Context context) {
    Intent starter = new Intent(context, AddStudentActivity.class);
    context.startActivity(starter);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_student);
    ButterKnife.bind(this);
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddStudentViewModel.class);

    subscribe();
  }

  private void subscribe() {
    add(RxTextView.textChanges(edtName).map(CharSequence::toString).subscribe(viewModel::setName));

    add(RxTextView.textChanges(edtAddress)
        .map(CharSequence::toString)
        .subscribe(viewModel::setAddress));

    add(RxTextView.textChanges(edtPhone)
        .map(CharSequence::toString)
        .subscribe(viewModel::setPhone));

    add(RxTextView.textChanges(edtSsn).map(CharSequence::toString).subscribe(viewModel::setSsn));

    add(RxTextView.textChanges(edtEmail)
        .map(CharSequence::toString)
        .subscribe(viewModel::setEmail));

    add(RxView.clicks(btnEnrol).subscribe(ignored -> {
      hideKeyboard();
      viewModel.enrolStudent();
    }));

    add(viewModel.getEnrolmentViewState().subscribe(this::render));
  }

  private void render(EnrolmentViewState viewState) {
    progressBar.setVisibility(viewState.progressVisiblility);
    btnEnrol.setVisibility(viewState.enrolButtonVisibility);

    edtName.setError(viewState.nameError);
    edtAddress.setError(viewState.addressError);
    edtPhone.setError(viewState.phoneError);
    edtSsn.setError(viewState.ssnError);
    edtEmail.setError(viewState.emailError);

    if (viewState.hasDataError) {
      String actionMessage = viewState.shouldRetry ? "Retry" : "Ok";
      showSnack(viewState.dataErrorMessage, actionMessage, v -> {
        if (viewState.shouldRetry) {
          viewModel.enrolStudent();
        }
      }, Snackbar.LENGTH_INDEFINITE);
    }

    if (viewState.student != null) {
      showSnack("Student enrolled", "ok", v -> {

      }, Snackbar.LENGTH_INDEFINITE);
    }
  }
}
