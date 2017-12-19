package com.khunzohn.rxstreamer.feature.news.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khunzohn.rxstreamer.R;
import com.khunzohn.rxstreamer.model.NewModel;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by khunzohn on 12/19/17.
 */

public final class NewsAdapter extends RecyclerView.Adapter<NewViewHolder> {

    private final PublishSubject<NewModel> itemClickSubject = PublishSubject.create();

    private List<NewModel> newModels;

    NewsAdapter() {
        newModels = new ArrayList<>();
    }

    @Override
    public NewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new, parent, false);
        NewViewHolder newViewHolder = new NewViewHolder(view);

        view.setOnClickListener(v -> {
            if (newViewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                itemClickSubject.onNext(newModels.get(newViewHolder.getAdapterPosition()));
            }
        });

        return newViewHolder;
    }

    @Override
    public void onBindViewHolder(NewViewHolder holder, int position) {
        holder.bind(newModels.get(position));
    }

    @Override
    public int getItemCount() {
        return newModels.size();
    }

    Observable<NewModel> getEventStream() {
        return itemClickSubject;
    }

    void setNewModels(List<NewModel> newModels) {
        int positionStart = this.newModels.size();
        this.newModels.addAll(newModels);
        notifyItemRangeInserted(positionStart, newModels.size());
    }
}
