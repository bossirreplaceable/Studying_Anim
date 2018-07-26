package com.yobo.studying_anim.lsn25_anim_parallax;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import java.util.ArrayList;
import java.util.List;

public class ParallaxContainer extends FrameLayout implements ViewPager.OnPageChangeListener {



    public ParallaxContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public void setUp(int... ids){

        List<ParallaxFragment> fragmentList=new ArrayList<>();
        for (int id : ids) {
            ParallaxFragment f = new ParallaxFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("layoutId", id);
            f.setArguments(bundle);
            fragmentList.add(f);
        }

        //实现一个ViewPager添加到FrameLayout
        ViewPager viewPager=new ViewPager(getContext());
        viewPager.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
       // viewPager.setId(R.id.b_vp);
        ParallaxAdapter adapter=new ParallaxAdapter(((FragmentActivity)getContext()).getSupportFragmentManager(),fragmentList);
        addView(viewPager,0);



         viewPager.addOnPageChangeListener(this);





    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
