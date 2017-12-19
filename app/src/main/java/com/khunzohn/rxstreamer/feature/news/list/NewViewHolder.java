package com.khunzohn.rxstreamer.feature.news.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.khunzohn.rxstreamer.R;
import com.khunzohn.rxstreamer.model.NewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by khunzohn on 12/19/17.
 */

class NewViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;

    NewViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(NewModel newModel) {
        tvTitle.setText(newModel.title());
        tvContent.setText(newModel.content());
    }
}
