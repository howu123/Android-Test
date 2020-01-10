package com.howu.lazyloadfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.google.android.material.tabs.TabLayout;
import com.howu.lazyloadfragment.adapter.LazyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private FourthFragment fourthFragment;
    private List<Fragment> fragmentList;
    private LazyFragmentPagerAdapter mLazyFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    public void initView() {
        mTabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.vp_tabs);
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
    }

    public void initData() {
        fragmentList = new ArrayList<>();
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();
        fourthFragment = new FourthFragment();
        fragmentList.add(firstFragment);
        fragmentList.add(secondFragment);
        fragmentList.add(thirdFragment);
        fragmentList.add(fourthFragment);
        //androidx fragment懒加载新方案 FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        mLazyFragmentPagerAdapter = new LazyFragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragmentList);
        mViewPager.setAdapter(mLazyFragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(fragmentList.size());
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText("First");
        mTabLayout.getTabAt(1).setText("Second");
        mTabLayout.getTabAt(2).setText("Third");
        mTabLayout.getTabAt(3).setText("Fourth");


    }
}
