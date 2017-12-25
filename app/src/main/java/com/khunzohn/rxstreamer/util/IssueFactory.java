package com.khunzohn.rxstreamer.util;

import android.content.Context;
import com.khunzohn.data.exception.Issue;
import com.khunzohn.rxstreamer.R;
import javax.inject.Inject;

/**
 * Created by khunzohn on 12/24/17.
 */

public class IssueFactory {

  private final Context context;

  @Inject
  public IssueFactory(Context context) {
    this.context = context;
  }

  public String getMessage(Issue issue) {
    switch (issue) {
      case API:
        return context.getString(R.string.error_message_api);
      case SERVER:
        return context.getString(R.string.error_message_server);
      case NETWORK:
        return context.getString(R.string.error_message_network);
      default:
        return context.getString(R.string.error_message_unknown);
    }
  }
}
