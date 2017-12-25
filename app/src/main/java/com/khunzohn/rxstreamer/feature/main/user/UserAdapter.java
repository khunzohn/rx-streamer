package com.khunzohn.rxstreamer.feature.main.user;

import android.view.View;
import com.khunzohn.rxstreamer.R;
import com.khunzohn.rxstreamer.feature.BaseRecyclerViewAdapter;
import com.khunzohn.rxstreamer.model.UserModel;

/**
 * Created by khunzohn on 12/25/17.
 */

public class UserAdapter extends BaseRecyclerViewAdapter<UserModel, UserViewHolder> {

  @Override public int getItemLayoutResource() {
    return R.layout.item_user;
  }

  @Override public UserViewHolder getViewHolder(View view) {
    return new UserViewHolder(view);
  }
}
