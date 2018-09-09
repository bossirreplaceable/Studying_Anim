package com.yobo.studying_anim.lsn29_scroll_qqmenu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
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
        mScroller = new Scroller(getContext(), null, true);
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

    /**
     * mScrollX 就是移动的偏移量
     * 要移动view到坐标点（100，100），
     * 那么我的偏移量就是(0,，0)  - （100，100） = （-100 ，-100）  ，
     * 我就要执行view.scrollTo(-100,-100),达到这个效果。
     * <p>
     * 所以向左以动的偏移量是正值 （0，0）-（50，50）=（-50，-50）【移动后的坐标是负值】
     * view.scrollBy(50,50);
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                showLog("按下的坐标：startX=" + startX);
                return true;

            case MotionEvent.ACTION_MOVE:
                showLog("抬起的坐标：endX=" + ev.getX());
                dx = ev.getX() - startX;
                dy = ev.getY() - startY;
                if (Math.abs(dx) - Math.abs(dy) > ViewConfiguration.getTouchSlop()) {
                    showLog("滑动的坐标：getScrollX=" + getScrollX());
                    showLog("滑动的距离：-dx=" + (-dx));
                    showLog("右边View的宽度：width=" + rightView.getWidth());
                    if (getScrollX() + (-dx) > rightView.getWidth() || getScrollX() + (-dx) < 0) {
                        return true;
                    }
                    showLog("滑动了---------------");
                    this.scrollBy((int) -dx, 0);
                    startX = ev.getX();
                    startY = ev.getY();
                    return true;
                }
                break;

            case MotionEvent.ACTION_UP:


                //仅仅只是把滑动的情况和参数描述和记录。
                //判断当前松开手是往左滑还是往右滑
                int offset = (getScrollX() / (float) rightView.getWidth()) > 0.5f ? rightView.getWidth() - getScrollX() : -getScrollX();
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


    private void showLog(String msg) {
        Log.e("D--slideDelete--", msg);
    }

    /**
     * 调用startScroll后执行该方法
     * startScroll方法会将需要以动偏移量值和起始坐标赋值
     * 然后在该方法中computeScrollOffset计算需要移动的正确偏移量
     * 然后调用scrollTo方法实现移动动画
     */
    @Override
    public void computeScroll() {

        if (mScroller.computeScrollOffset()) {
            showLog("computeScroll:getCurrX=" + mScroller.getCurrX());
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }


    }
}
