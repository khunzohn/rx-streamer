package com.khunzohn.rxstreamer.feature;

import android.arch.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by khunzohn on 12/18/17.
 */

public class BaseViewModel extends ViewModel {

  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  public void add(Disposable disposable) {
    compositeDisposable.add(disposable);
  }

  @Override
  protected void onCleared() {
    super.onCleared();
    if (!compositeDisposable.isDisposed()) {
      compositeDisposable.clear();
    }
  }
}
