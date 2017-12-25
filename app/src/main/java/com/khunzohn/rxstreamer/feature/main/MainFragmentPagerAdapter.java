package com.khunzohn.rxstreamer.feature.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;

/**
 * Created by khunzohn on 12/23/17.
 */

public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {

  private List<Fragment> fragments;
  private List<String> titles;

  MainFragmentPagerAdapter(FragmentManager fm, @NonNull List<Fragment> fragments,
      List<String> titles) {
    super(fm);
    this.fragments = fragments;
    this.titles = titles;
  }

  @Override public Fragment getItem(int position) {
    return fragments.get(position);
  }

  @Override public int getCount() {
    return fragments.size();
  }

  @Nullable @Override public CharSequence getPageTitle(int position) {
    return titles.get(position);
  }
}
