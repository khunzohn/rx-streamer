package com.khunzohn.rxstreamer.feature.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.khunzohn.rxstreamer.R;
import com.khunzohn.rxstreamer.feature.BaseActivity;
import com.khunzohn.rxstreamer.feature.main.repository.RepositoriesFragment;
import com.khunzohn.rxstreamer.feature.main.user.UsersFragment;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector{

  @Inject
  DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.layout_tab) TabLayout layoutTab;
  @BindView(R.id.view_pager) ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setUpToolbar();

    setUpPager();
  }

  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(R.string.app_name);
    }
  }

  private void setUpPager() {
    List<Fragment> fragmentList = new ArrayList<>();
    fragmentList.add(UsersFragment.getInstance());
    fragmentList.add(RepositoriesFragment.getInstance());

    List<String> titles = new ArrayList<>();
    titles.add("Users");
    titles.add("Repositories");

    MainFragmentPagerAdapter fragmentPagerAdapter =
        new MainFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
    viewPager.setAdapter(fragmentPagerAdapter);

    layoutTab.setupWithViewPager(viewPager);
  }

  @Override public AndroidInjector<Fragment> supportFragmentInjector() {
    return dispatchingAndroidInjector;
  }
}
