package com.rachelzhang.gallery;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DetailViewPagerAdapter extends FragmentPagerAdapter {

  public DetailViewPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(int i) {
    return new DetailViewPagerFragment();
  }

  @Override
  public int getCount() {
    return 3;
  }
}
