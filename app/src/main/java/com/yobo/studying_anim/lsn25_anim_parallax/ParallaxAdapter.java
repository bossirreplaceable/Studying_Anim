package com.yobo.studying_anim.lsn25_anim_parallax;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ParallaxAdapter extends FragmentPagerAdapter {


    private List<ParallaxFragment> fragmentList;

    public ParallaxAdapter(FragmentManager fm, List<ParallaxFragment> list) {
        super(fm);
        this.fragmentList = list;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
