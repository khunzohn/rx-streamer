package com.khunzohn.rxstreamer.feature;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by khunzohn on 12/25/17.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
  public BaseViewHolder(View itemView) {
    super(itemView);
  }

  public abstract void bind(T model);
}
