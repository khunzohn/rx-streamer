package com.khunzohn.rxstreamer.feature;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.khunzohn.rxstreamer.R;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by khunzohn on 12/23/17.
 */

public abstract class BaseFragment extends Fragment {

  private CompositeDisposable compositeDisposable;
  private Unbinder unbinder;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    compositeDisposable = new CompositeDisposable();
  }

  @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(getLayoutResource(), container, false);
    unbinder = ButterKnife.bind(this,view);
    return view;
  }

  @LayoutRes
  public abstract int getLayoutResource();

  public void showSnack(String message, int actionMessage, View.OnClickListener onClickListener,
      int duration) {
    View rootContent = getActivity().findViewById(android.R.id.content);
    Snackbar.make(rootContent, message, duration)
        .setAction(actionMessage, onClickListener)
        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
        .show();
  }

  public void add(Disposable disposable) {
    if (compositeDisposable != null) {
      compositeDisposable.add(disposable);
    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
      compositeDisposable.clear();
    }

    if (unbinder != null) {
      unbinder.unbind();
    }
  }
}
