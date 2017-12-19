package com.khunzohn.rxstreamer.util;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * Created by khunzohn on 12/18/17.
 */
@IntDef({ VISIBLE, INVISIBLE, GONE })
@Retention(RetentionPolicy.SOURCE)
public @interface Visibility {
}