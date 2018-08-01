package com.yobo.studying_anim.lsn29_scroll_qqmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

public class SlideMenu extends HorizontalScrollView {


    private int mScreenWidth;
    private ViewGroup mMain;
    private ViewGroup mMenu;
    private int mMenuPadingEnd;
    private int mMenuWidth;
    private boolean isOnce = false;

    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        /*
         * 首先获取到屏幕的宽度
         */
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        assert wm != null;
        wm.getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        mMenuPadingEnd = mScreenWidth / 3;
    }


    /**
     * 如果宽高设置成wrap，那onMeasure并不能一次就搞定所有子View的宽高。
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (!isOnce) {
            LinearLayout mFather = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mFather.getChildAt(0);
            mMain = (ViewGroup) mFather.getChildAt(1);
            //将滑动菜单设置成屏幕宽度的三分之一
            mMenuWidth = mScreenWidth - mMenuPadingEnd;
            mMain.getLayoutParams().width = mScreenWidth;
            mMenu.getLayoutParams().width = mMenuWidth;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //在第一次初始化布局的时候，将整张屏幕整体向左滑动菜单的宽度，这样刚好只露出mMain视图。
//        if (changed) {
//            this.scrollTo(mMenuWidth, 0);
//            isOnce = true;
//        }
        super.onLayout(changed, l, t, r, b);
    }


    private float downX;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX=ev.getX();
                break;
            case MotionEvent.ACTION_UP:
                float upX=ev.getX();
                float moveX=upX-downX;
                if (moveX<mScreenWidth/3){
                    scrollTo(mMenuWidth,0);
                }else {
                    scrollTo(0,0);
                }
                return true;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }


    /**
     * 在onTouchEvent中调用了scrollTo()方法，才会触发该方法。
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //处理动画
        //滑动百分比因子
        float factor = (float)l / mMenuWidth;
        //1.平移效果(兼容2.x，nineOldeAndroid.jar)
//		mMenu.setTranslationX(mMenuWidth*);
        //滑动的速度应该比主界面慢一点
        ViewHelper.setTranslationX(mMenu, mMenuWidth*factor*0.6f);
        //2.缩放效果
        float leftScale = 1-0.4f*factor;
        ViewHelper.setScaleX(mMenu, leftScale);
        ViewHelper.setScaleY(mMenu, leftScale);

        float rightScale = 0.8f+0.2f*factor;
        ViewHelper.setScaleX(mMain, rightScale);
        ViewHelper.setScaleY(mMain, rightScale);
        //3.透明度效果
        ViewHelper.setAlpha(mMenu, 1-factor);

    }
}
