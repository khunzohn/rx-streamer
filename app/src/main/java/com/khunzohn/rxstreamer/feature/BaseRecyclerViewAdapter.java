package com.khunzohn.rxstreamer.feature;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by khunzohn on 12/25/17.
 */

public abstract class BaseRecyclerViewAdapter<T, V extends BaseViewHolder<T>>
    extends RecyclerView.Adapter<V> {
  private List<T> models;
  private PublishSubject<T> modelSubject;

  public BaseRecyclerViewAdapter() {
    models = new ArrayList<>();
    modelSubject = PublishSubject.create();
  }

  @LayoutRes
  public abstract int getItemLayoutResource();

  public abstract V getViewHolder(View view);

  @Override public V onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(getItemLayoutResource(), parent, false);
    V viewHolder = getViewHolder(view);

    view.setOnClickListener(v -> {
      if (viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
        modelSubject.onNext(models.get(viewHolder.getAdapterPosition()));
      }
    });

    return viewHolder;
  }

  @Override public void onBindViewHolder(V holder, int position) {
    holder.bind(models.get(position));
  }

  @Override public int getItemCount() {
    return models.size();
  }

  public Observable<T> onItemClickStream() {
    return modelSubject;
  }

  public void setModels(List<T> models) {
    this.models = models;
    notifyDataSetChanged();
  }
}
