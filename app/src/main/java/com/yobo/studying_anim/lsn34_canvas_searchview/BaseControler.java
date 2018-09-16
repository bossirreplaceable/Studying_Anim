package com.yobo.studying_anim.lsn34_canvas_searchview;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

public abstract class BaseControler {

    public static final int STATE_ANIM_NONE = 0;
    public static final int STATE_ANIM_START = 1;
    public static final int STATE_ANIM_STOP = 2;
    public static final int DEFAULT_ANIM_TIME = 5000;
    public static final float DEFAULT_ANIM_STARTF = 0;
    public static final float DEFAULT_ANIM_ENDF = 1;
    private SearchView mySearchView;
    public int mState = STATE_ANIM_NONE;
    public float mpro = -1;
    public abstract void drawCanvas(Canvas canvas, Paint paint);
    public void startAnim() {
        startAnimValueFloat();
    }
    public int getWidth() {
        return mySearchView.getWidth();
    }

    public int getHeight() {
        return mySearchView.getHeight();
    }

    private void startAnimValueFloat() {

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(1300);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mpro = (float) animation.getAnimatedValue();
                mySearchView.invalidate();
            }
        });
        valueAnimator.start();
    }

    public void setSearView(SearchView searchView) {
        this.mySearchView = searchView;
    }
    public void stopAnim() {


    }
}
