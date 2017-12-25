package com.khunzohn.rxstreamer.feature.main.user;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.khunzohn.rxstreamer.R;
import com.khunzohn.rxstreamer.feature.BaseViewHolder;
import com.khunzohn.rxstreamer.model.UserModel;

/**
 * Created by khunzohn on 12/25/17.
 */

public class UserViewHolder extends BaseViewHolder<UserModel> {

  @BindView(R.id.tv_name) TextView tvName;

  public UserViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  @Override public void bind(UserModel model) {
    tvName.setText(model.login());
  }
}
