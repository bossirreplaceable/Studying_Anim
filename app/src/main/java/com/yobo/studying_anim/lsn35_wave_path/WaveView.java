package com.yobo.studying_anim.lsn35_wave_path;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class WaveView extends View {

    private Paint paint;
    private Path path;
    private Path circlePath;
    public WaveView(Context context) {
        super(context);
        init();
    }

    private void init() {
        path=new Path();
        circlePath=new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(6);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //波长
    private int waveLength = 200;
    //波峰
    private int waveHeight = 70;
    //波的起始高度, 要想实现一直上涨，不断改变initY的值就行了
    private int initY = 900;
    //波浪移动的偏移值
    private int dx=-1;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.BLUE);
//        setLayerType(LAYER_TYPE_SOFTWARE,null);
        canvas.save();
        path.reset();
        path.moveTo(-waveLength+dx, initY);

        for (int i = -waveLength; i < getWidth() + waveLength; i += waveLength) {
            path.rQuadTo(waveLength / 4, waveHeight, waveLength/2, 0);
            path.rQuadTo(waveLength / 4, -waveHeight, waveLength/2, 0);
        }
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();
        circlePath.addCircle(getWidth()/2,900,400, Path.Direction.CW);
//        canvas.clipRect(200,200,900,1000);
        canvas.clipPath(circlePath);
        canvas.drawPath(path, paint);
        canvas.restore();
    }
    @SuppressLint("WrongConstant")
    public void startAnim(){
        ValueAnimator animator=ValueAnimator.ofInt(0,waveLength);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx= (int) animation.getAnimatedValue();   postInvalidate();
            }
        });
        animator.start();
    }


}
