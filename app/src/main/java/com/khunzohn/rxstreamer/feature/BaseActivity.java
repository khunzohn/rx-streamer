package com.khunzohn.rxstreamer.feature;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.khunzohn.data.network.interceptor.HttpErrorEvent;
import com.khunzohn.rxstreamer.R;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by khunzohn on 12/19/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  public void add(Disposable disposable) {
    compositeDisposable.add(disposable);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    add(HttpErrorEvent.subscribe(this::handle));
  }

  private void handle(HttpErrorEvent.Event event) {

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (!compositeDisposable.isDisposed()) {
       compositeDisposable.clear();
    }
  }

  public void showSnack(String message, int actionMessage, View.OnClickListener onClickListener,
      int duration) {
    View rootContent = findViewById(android.R.id.content);
    Snackbar.make(rootContent, message, duration)
        .setAction(actionMessage, onClickListener)
        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
        .show();
  }

  public void showSnack(String message, String actionMessage, View.OnClickListener onClickListener,
      int duration) {
    View rootContent = findViewById(android.R.id.content);
    Snackbar.make(rootContent, message, duration)
        .setAction(actionMessage, onClickListener)
        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
        .show();
  }

  public void hideKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }
}
