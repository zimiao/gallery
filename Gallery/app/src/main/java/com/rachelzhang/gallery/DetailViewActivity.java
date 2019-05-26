package com.rachelzhang.gallery;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class DetailViewActivity extends FragmentActivity {
  private ViewPager mDetailViewPager;
  private DetailViewPagerAdapter mPagerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.detail_view_activity);
    mDetailViewPager = findViewById(R.id.detail_view_pager);
    mPagerAdapter = new DetailViewPagerAdapter(getSupportFragmentManager());
    mDetailViewPager.setAdapter(mPagerAdapter);
  }
}
