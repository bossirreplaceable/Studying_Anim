package com.yobo.studying_anim.lsn29_scroll_qqmenu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class SlideItemMenu extends LinearLayout {


    private Scroller mScroller;
    private View leftView;
    private View rightView;

    private float startX;
    private float startY;

    public SlideItemMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.HORIZONTAL);
        mScroller = new Scroller(getContext(),null,true);
    }

    /**
     * 在布局加载完成之后，获取到左右两个子View
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        leftView = getChildAt(0);
        rightView = getChildAt(1);
    }

    /**
     * 在事件分发方法中，截取完想要的动作以后，消费掉点击事件
     * ViewConfiguration.getTouchSlop()--手机屏幕能监听到滑动的最小距离
     */
    private float dx;
    private float dy;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                return true;

            case MotionEvent.ACTION_MOVE:

                dx = ev.getX() - startX;
                dy = ev.getY() - startY;
                if (Math.abs(dx) - Math.abs(dy) > ViewConfiguration.getTouchSlop()) {
                    if (getScrollX() + (-dx) > rightView.getWidth() || getScrollX() + (-dx) < 0) {
                        return true;
                    }

                    this.scrollBy((int) -dx, 0);
                    startX=ev.getX();
                    startY=ev.getY();
                    return true;
                }
                break;

            case MotionEvent.ACTION_UP:



                //仅仅只是把滑动的情况和参数描述和记录。
                //判断当前松开手是往左滑还是往右滑
                int offset = (getScrollX()/(float)rightView.getWidth()) > 0.5f ? rightView.getWidth()-getScrollX() : -getScrollX();
                mScroller.startScroll(getScrollX(), getScrollY(), offset, 0);
                invalidate();
                startX = 0;
                startY = 0;
                dx = 0;
                dy = 0;


                break;

            default:
                break;
        }


        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void computeScroll() {

        if(mScroller.computeScrollOffset()){
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }


    }
}
