package com.example.marhacker.rss;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class pageAdapter extends FragmentStatePagerAdapter {
    int tabs;
    ArrayList<Fragment> fragments;

    public pageAdapter(FragmentManager fm){
        super(fm);
        this.tabs=tabs;
        fragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
       return fragments.get(position);
    }

public void add(Fragment fragment)
{
    fragments.add(fragment);
}
    @Override
    public int getCount() {
        return fragments.size();
    }
}
